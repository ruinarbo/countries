package com.aboykov.countries.data.remote

import com.aboykov.countries.common.Constants.COUNTRIES_JSON_FILE_PATH
import com.aboykov.countries.data.remote.dto.CountryDto
import retrofit2.http.GET

interface CountriesApi {
    @GET(COUNTRIES_JSON_FILE_PATH)
    suspend fun getCountries(): List<CountryDto>
}