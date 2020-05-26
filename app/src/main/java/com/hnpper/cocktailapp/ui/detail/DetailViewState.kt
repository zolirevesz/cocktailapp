package com.hnpper.cocktailapp.ui.detail

import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.User

sealed class DetailViewState

object Loading : DetailViewState()

data class Loaded (val user: User): DetailViewState()