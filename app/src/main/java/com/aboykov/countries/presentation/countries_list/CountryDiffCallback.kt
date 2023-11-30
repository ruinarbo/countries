package com.aboykov.countries.presentation.countries_list

import androidx.recyclerview.widget.DiffUtil
import com.aboykov.countries.domain.model.Country
/**
 * DiffUtil callback for countries list
 */
class CountryDiffCallback(
    private val oldList: List<Country>,
    private val newList: List<Country>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
                && oldList[oldItemPosition].code == newList[newItemPosition].code
                && oldList[oldItemPosition].capital == newList[newItemPosition].capital
                && oldList[oldItemPosition].region == newList[newItemPosition].region
    }

}