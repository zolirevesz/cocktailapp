package com.hnpper.cocktailapp.ui.detail

import com.hnpper.cocktailapp.model.Cocktail

sealed class DetailViewState

object Loading: DetailViewState()

data class Loaded (val cocktail: Cocktail): DetailViewState()