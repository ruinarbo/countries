package com.aboykov.countries.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.aboykov.countries.R
/**
 * Main hosting activity to host single fragment.
 * To host multiple fragments navigation has to be implemented.
*/
class CountriesMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}