package com.aboykov.countries.presentation.countries_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aboykov.countries.CountriesApplication
import com.aboykov.countries.R
import com.aboykov.countries.presentation.viewModelFactory

class CountriesListFragment : Fragment(R.layout.fragment_countries_list) {

    private lateinit var countriesListViewModel: CountriesListViewModel
    private lateinit var countriesListAdapter: CountriesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        countriesListAdapter = CountriesListAdapter(emptyList())
        recyclerView.adapter = countriesListAdapter

        val countriesListViewModelFactory = viewModelFactory {
            CountriesListViewModel(
                getCountriesUseCase = CountriesApplication.countriesAppModule.getCountriesUseCase
            )
        }

        countriesListViewModel =
            ViewModelProvider(
                owner = this,
                factory = countriesListViewModelFactory
            )[CountriesListViewModel::class.java]

        countriesListViewModel.countries.observe(viewLifecycleOwner) { countries ->
            countriesListAdapter.updateData(
                newCountries = countries
            )
        }
        countriesListViewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }
        countriesListViewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
            }
        }
    }

}