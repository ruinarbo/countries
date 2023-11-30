package com.aboykov.countries.domain.repository

import com.aboykov.countries.data.remote.dto.CountryDto

interface CountriesRepository {
    suspend fun getCountries(): List<CountryDto>

}