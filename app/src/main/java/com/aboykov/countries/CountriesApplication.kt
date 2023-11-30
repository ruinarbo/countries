package com.aboykov.countries

import android.app.Application
import com.aboykov.countries.di.CountriesAppModule
import com.aboykov.countries.di.CountriesAppModuleImpl

class CountriesApplication : Application() {
    companion object {
        lateinit var countriesAppModule: CountriesAppModule
    }

    override fun onCreate() {
        super.onCreate()
        countriesAppModule = CountriesAppModuleImpl(this)
    }
}