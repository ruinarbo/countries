package com.aboykov.countries.presentation.countries_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aboykov.countries.common.Resource
import com.aboykov.countries.domain.model.Country
import com.aboykov.countries.domain.use_case.GetCountriesUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CountriesListViewModel(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    init {
        getCountries()
    }

    fun getCountries() {
        getCountriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _countries.value = result.data ?: emptyList()
                }

                is Resource.Error -> {
                    _error.value = result.message ?: "An unexpected error occurred"
                }

                is Resource.Loading -> {
                    _loading.value = true
                }
            }
        }.launchIn(viewModelScope)
    }

}