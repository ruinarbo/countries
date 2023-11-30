package com.aboykov.countries.domain.model
/**
 * Country is a data class that defines the properties of a country.
 * It is used to map the data from the API to the domain layer.
 */
data class Country(
    val code: String,
    val name: String,
    val region: String,
    val capital: String
)
