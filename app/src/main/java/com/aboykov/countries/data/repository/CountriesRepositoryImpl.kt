package com.aboykov.countries.data.repository

import com.aboykov.countries.data.remote.CountriesApi
import com.aboykov.countries.domain.repository.CountriesRepository
import com.aboykov.countries.data.remote.dto.CountryDto
/**
 * CountriesRepositoryImpl is a class that implements the CountriesRepository interface.
 */
class CountriesRepositoryImpl(
    private val countriesApi: CountriesApi
) : CountriesRepository {
    override suspend fun getCountries(): List<CountryDto> {
        return countriesApi.getCountries()
    }
}