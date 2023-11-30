package com.aboykov.countries.presentation.countries_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aboykov.countries.R
import com.aboykov.countries.domain.model.Country
/**
 * Adapter for countries list used in [CountriesListFragment]
 * CountryDiffCallback is used to update data in adapter
 */
class CountriesListAdapter(private var countries: List<Country>) :
    RecyclerView.Adapter<CountriesListAdapter.CountryViewHolder>() {

    fun updateData(newCountries: List<Country>) {
        val diffResult = DiffUtil.calculateDiff(CountryDiffCallback(countries, newCountries))
        countries = newCountries
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int = countries.size

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(country: Country) {
            itemView.findViewById<TextView>(R.id.countryNameRegion).text =
                "${country.name}, ${country.region}"
            itemView.findViewById<TextView>(R.id.countryCode).text = country.code
            itemView.findViewById<TextView>(R.id.countryCapital).text = country.capital
        }
    }
}
