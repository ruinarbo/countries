package com.aboykov.countries.domain.use_case

import com.aboykov.countries.common.Resource
import com.aboykov.countries.domain.model.Country
import com.aboykov.countries.domain.repository.CountriesRepository
import com.aboykov.countries.data.remote.dto.toCountry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
/**
 * GetCountriesUseCase is a class that is used to get a list
 * of countries from the repository.It implements following flow:
 * 1. Loading state
 * 2. Success state
 * And handles HttpException and IOException and could be extended to handle other errors.
 **/
class GetCountriesUseCase(
    private val repository: CountriesRepository
) {
    operator fun invoke(): Flow<Resource<List<Country>>> = flow {
        try {
            emit(Resource.Loading())
            val countries = repository.getCountries().map { it.toCountry() }
            emit(Resource.Success(countries))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("No internet connection"))
        }
    }


}