package com.aboykov.countries.data.remote.dto

import com.aboykov.countries.domain.model.Country
/**
 * CountryDto is a data class that defines the object received from API
 * and maps it's data to Country object.
 */
data class CountryDto(
    val capital: String,
    val code: String,
    val currency: Currency,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String
)

fun CountryDto.toCountry() = Country(
    code = code,
    name = name,
    region = region,
    capital = capital
)