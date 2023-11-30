package com.aboykov.countries.domain.use_case

import com.aboykov.countries.common.Resource
import com.aboykov.countries.domain.model.Country
import com.aboykov.countries.domain.repository.CountriesRepository
import com.aboykov.countries.data.remote.dto.toCountry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

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