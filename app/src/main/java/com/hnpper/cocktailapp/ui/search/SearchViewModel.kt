package com.hnpper.cocktailapp.ui.search

import co.zsmb.rainbowcake.base.JobViewModel
import com.hnpper.cocktailapp.model.Cocktail
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchPresenter: SearchPresenter
) : JobViewModel<SearchViewState>(Loading) {

    lateinit var cocktailList: List<Cocktail>

    fun loadData() = execute {
        viewState = Loaded
    }

    fun loadSearch(name : String) = execute {
        viewState = GetSearchResult
        cocktailList = listOf()
        val responselist = searchPresenter.getCocktailsByName(name)
        cocktailList = responselist.drinks
        if (cocktailList.isNotEmpty()) {
            viewState = SearchLoaded
        } else {
            viewState = Loaded
        }
    }

}