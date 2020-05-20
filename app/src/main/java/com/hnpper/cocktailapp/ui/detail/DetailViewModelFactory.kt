package com.hnpper.cocktailapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hnpper.cocktailapp.data.disk.CocktailRepository

class DetailViewModelFactory(
    private val cocktailRepository: CocktailRepository,
    private val idDrink: Int
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(
            cocktailRepository,
            idDrink
        ) as T
    }
}