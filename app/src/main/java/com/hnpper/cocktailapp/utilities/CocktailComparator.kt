package com.hnpper.cocktailapp.utilities

import androidx.recyclerview.widget.DiffUtil
import com.hnpper.cocktailapp.model.Cocktail

object CocktailComparator : DiffUtil.ItemCallback<Cocktail>() {

    override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
        return oldItem.idDrink == newItem.idDrink
    }

    override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
        return (oldItem.idDrink == newItem.idDrink && oldItem.strDrink.equals(newItem.strDrink))
    }
}
