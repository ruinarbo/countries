package com.aboykov.countries.data.remote.dto

import com.aboykov.countries.domain.model.Country

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