package com.aboykov.countries.domain.repository

import com.aboykov.countries.data.remote.dto.CountryDto
/**
 * CountriesRepository is an interface that defines the methods
 * that must be implemented by any class.
 */
interface CountriesRepository {
    suspend fun getCountries(): List<CountryDto>

}