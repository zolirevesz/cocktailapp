package com.hnpper.cocktailapp.ui.home

import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.User

sealed class HomeViewState

object Loading : HomeViewState()

data class HomeLoaded(val user : User) : HomeViewState()

object LoggedOut : HomeViewState()