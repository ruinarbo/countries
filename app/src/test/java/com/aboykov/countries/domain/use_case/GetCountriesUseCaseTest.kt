@file:Suppress("DEPRECATION")

package com.aboykov.countries.domain.use_case

import com.aboykov.countries.common.Resource
import com.aboykov.countries.data.remote.dto.CountryDto
import com.aboykov.countries.data.remote.dto.Currency
import com.aboykov.countries.data.remote.dto.Language
import com.aboykov.countries.data.remote.dto.toCountry
import com.aboykov.countries.domain.model.Country
import com.aboykov.countries.domain.repository.CountriesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi
class GetCountriesUseCaseTest{
    private lateinit var getCountriesUseCase: GetCountriesUseCase
    private lateinit var repository: CountriesRepository

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setUp() {
        repository = mockk()
        getCountriesUseCase = GetCountriesUseCase(repository)
    }

    @Test
    fun `invoke success`() = testScope.runBlockingTest {
        val currency = Currency("", "", "")
        val language = Language("", "")
        val countries = listOf(
            CountryDto("Country1", "Region1", currency,"Code1", language,"","Capital1"),
            CountryDto("Country2", "Region2", currency,"Code2", language,"","Capital2")
        )
        coEvery { repository.getCountries() } returns countries

        val result = getCountriesUseCase.invoke().toList()

        assertTrue(result.size == 2)
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertEquals((result[1] as Resource.Success).data, countries.map { it.toCountry() })
    }

    @Test
    fun `invoke error`() = testScope.runBlockingTest {
        val errorMessage = "HTTP 404 Response.error()"
        coEvery { repository.getCountries() } throws HttpException(
            Response.error<List<Country>>(404, errorMessage.toResponseBody())
        )

        val result = getCountriesUseCase.invoke().toList()

        assertTrue(result.size == 2)
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
        assertEquals((result[1] as Resource.Error).message, errorMessage)
    }

    @Test
    fun `invoke no internet connection`() = testScope.runBlockingTest {
        coEvery { repository.getCountries() } throws IOException("No internet connection")

        val result = getCountriesUseCase.invoke().toList()

        assertTrue(result.size == 2)
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
        assertEquals((result[1] as Resource.Error).message, "No internet connection")
    }
}
