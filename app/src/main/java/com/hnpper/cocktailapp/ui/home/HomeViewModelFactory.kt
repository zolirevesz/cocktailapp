package com.hnpper.cocktailapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hnpper.cocktailapp.data.disk.CocktailRepository

class HomeViewModelFactory (private val repository: CocktailRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = HomeViewModel(repository) as T
}