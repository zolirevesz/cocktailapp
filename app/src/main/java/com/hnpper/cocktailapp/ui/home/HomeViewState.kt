package com.hnpper.cocktailapp.ui.home

import com.hnpper.cocktailapp.data.Cocktail

sealed class HomeViewState

object Loading : HomeViewState()

data class HomeLoaded(
    val cocktailList : List<Cocktail>
) : HomeViewState()