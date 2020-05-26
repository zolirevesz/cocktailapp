package com.hnpper.cocktailapp.ui.search

import co.zsmb.rainbowcake.base.JobViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchPresenter: SearchPresenter
) : JobViewModel<SearchViewState>(Loading) {

    fun loadData() = execute {
        viewState = Loaded
    }

}