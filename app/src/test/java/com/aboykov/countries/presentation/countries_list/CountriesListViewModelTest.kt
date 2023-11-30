@file:Suppress("DEPRECATION")

package com.aboykov.countries.presentation.countries_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aboykov.countries.common.Resource
import com.aboykov.countries.domain.model.Country
import com.aboykov.countries.domain.use_case.GetCountriesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CountriesListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getCountriesUseCase: GetCountriesUseCase
    private lateinit var viewModel: CountriesListViewModel
    private lateinit var countriesObserver: Observer<List<Country>>
    private lateinit var errorObserver: Observer<String>
    private lateinit var loadingObserver: Observer<Boolean>

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(this.testDispatcher)

    @Before
    fun setUp() {
        getCountriesUseCase = mockk()
        viewModel = CountriesListViewModel(getCountriesUseCase)
        countriesObserver = mockk(relaxed = true)
        errorObserver = mockk(relaxed = true)
        loadingObserver = mockk(relaxed = true)

    }

    @Test
    fun `getCountries success`() = testScope.runBlockingTest {
        // Arrange
        val countries = listOf(
            Country("Country1", "Region1", "Code1", "Capital1"),
            Country("Country2", "Region2", "Code2", "Capital2")
        )
        coEvery { getCountriesUseCase.invoke() } returns flowOf(Resource.Success(countries))
        viewModel.countries.observeForever(countriesObserver)
        viewModel.error.observeForever(errorObserver)
        viewModel.loading.observeForever(loadingObserver)
        viewModel.getCountries()
        verify { countriesObserver.onChanged(countries) }
        verify { errorObserver.onChanged("") }
        verify { loadingObserver.onChanged(false) }
    }

    @Test
    fun `getCountries error`() = testScope.runBlockingTest {
        val errorMessage = "An error occurred"
        coEvery { getCountriesUseCase.invoke() } returns flowOf(Resource.Error(errorMessage))
        viewModel.error.observeForever(errorObserver)
        viewModel.loading.observeForever(loadingObserver)
        viewModel.getCountries()
        verify { errorObserver.onChanged(errorMessage) }
        verify { loadingObserver.onChanged(false) }
    }

}