package com.hnpper.cocktailapp.ui.search

sealed class SearchViewState

object Loading: SearchViewState()

object Loaded: SearchViewState()