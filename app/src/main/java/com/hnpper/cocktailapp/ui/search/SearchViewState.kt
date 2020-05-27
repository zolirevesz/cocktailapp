package com.hnpper.cocktailapp.ui.search

sealed class SearchViewState

object Loading: SearchViewState()

object Loaded: SearchViewState()

object GetSearchResult: SearchViewState()

object SearchLoaded: SearchViewState()