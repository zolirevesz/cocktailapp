package com.hnpper.cocktailapp.ui.detail

sealed class DetailViewState

object Loading: DetailViewState()

object Loaded: DetailViewState()