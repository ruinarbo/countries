package com.aboykov.countries.data.repository

import com.aboykov.countries.data.remote.CountriesApi
import com.aboykov.countries.data.remote.dto.CountryDto
import com.aboykov.countries.data.remote.dto.Currency
import com.aboykov.countries.data.remote.dto.Language
import com.aboykov.countries.domain.repository.CountriesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class CountriesRepositoryImplTest{
    @Test
    fun `getCountries success`() = runBlocking {
        // Arrange
        val countriesApi = mockk<CountriesApi>()
        val repository: CountriesRepository = CountriesRepositoryImpl(countriesApi)

        val currency = Currency("", "", "")
        val language = Language("", "")
        val countries = listOf(
            CountryDto("Country1", "Region1", currency,"Code1", language,"","Capital1"),
            CountryDto("Country2", "Region2", currency,"Code2", language,"","Capital2")
        )
        coEvery { countriesApi.getCountries() } returns countries
        val result = repository.getCountries()
        assertEquals(countries, result)
    }
}