package com.hnpper.cocktailapp.ui.home

import androidx.lifecycle.ViewModel
import co.zsmb.rainbowcake.base.JobViewModel
import com.hnpper.cocktailapp.data.Cocktail
import com.hnpper.cocktailapp.remote.RemoteServiceInterface
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homePresenter: HomePresenter
) : JobViewModel<HomeViewState>(Loading){

    fun load() = execute {
        viewState = HomeLoaded(cocktailList = listOf(
            Cocktail(1, "tempCocktail", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null),
            webservice.getCocktailById(11007, "1")
            ))

    }

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(RemoteServiceInterface::class.java)
    }

}