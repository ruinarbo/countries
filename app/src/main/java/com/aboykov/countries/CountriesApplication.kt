package com.aboykov.countries

import android.app.Application
import com.aboykov.countries.di.CountriesAppModule
import com.aboykov.countries.di.CountriesAppModuleImpl
/**
 * CountriesApplication is the main application class.
 * Created to initialize the CountriesAppModule on application start.
 * And to provide access to the CountriesAppModule from anywhere in the application.
 */
class CountriesApplication : Application() {
    companion object {
        lateinit var countriesAppModule: CountriesAppModule
    }

    override fun onCreate() {
        super.onCreate()
        countriesAppModule = CountriesAppModuleImpl(this)
    }
}