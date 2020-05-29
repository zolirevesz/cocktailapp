package com.hnpper.cocktailapp.ui.home

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.zsmb.rainbowcake.base.JobViewModel
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.remote.RemoteServiceInterface
import com.google.gson.GsonBuilder
import com.hnpper.cocktailapp.data.disk.CocktailRepository
import com.hnpper.cocktailapp.ui.search.GetSearchResult
import com.hnpper.cocktailapp.ui.search.Loaded
import com.hnpper.cocktailapp.ui.search.SearchLoaded
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homePresenter: HomePresenter
) : JobViewModel<HomeViewState>(Loading) {

    lateinit var cocktailList: MutableList<Cocktail>

    fun loadData() = execute {
        viewState = Loading
        if (homePresenter.existActiveUser()) {
            val user = homePresenter.getUser()
            if (!user.favCocktailsId.isNullOrEmpty()) {
                loadList(user.favCocktailsId!!)
            }
            viewState = HomeLoaded(user)
        } else {
            viewState = HomeWithoutLogin
        }
    }

    private fun loadList(favCocktailsId: MutableList<Int>) = execute {
        cocktailList = mutableListOf<Cocktail>()
        for (id in favCocktailsId) {
                cocktailList.add(homePresenter.getCocktailById(id))
        }
    }

    fun logout() = execute {
        viewState = Loading
        if (homePresenter.logout()) {
            viewState = LoggedOut
        }
    }
}