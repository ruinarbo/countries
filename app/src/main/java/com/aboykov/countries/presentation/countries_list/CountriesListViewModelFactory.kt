package com.aboykov.countries.presentation.countries_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aboykov.countries.domain.use_case.GetCountriesUseCase

class CountriesListViewModelFactory(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountriesListViewModel::class.java)) {
            return CountriesListViewModel(getCountriesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}