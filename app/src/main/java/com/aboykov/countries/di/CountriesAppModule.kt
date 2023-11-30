package com.aboykov.countries.di

import android.content.Context
import com.aboykov.countries.common.Constants
import com.aboykov.countries.data.remote.CountriesApi
import com.aboykov.countries.data.repository.CountriesRepositoryImpl
import com.aboykov.countries.domain.repository.CountriesRepository
import com.aboykov.countries.domain.use_case.GetCountriesUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CountriesAppModule {
    val countriesApi: CountriesApi
    val countriesRepository: CountriesRepository
    val getCountriesUseCase: GetCountriesUseCase
}

class CountriesAppModuleImpl(
    private val appContext: Context
) : CountriesAppModule {
    override val countriesApi: CountriesApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }
    override val countriesRepository: CountriesRepository by lazy {
        CountriesRepositoryImpl(countriesApi)
    }
    override val getCountriesUseCase: GetCountriesUseCase by lazy {
        GetCountriesUseCase(countriesRepository)
    }

}