package com.hnpper.cocktailapp.ui.detail

import android.net.Uri
import co.zsmb.rainbowcake.base.JobViewModel
import com.google.gson.GsonBuilder
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.User
import com.hnpper.cocktailapp.remote.RemoteServiceInterface
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val detailPresenter: DetailPresenter
) : JobViewModel<DetailViewState>(Loading) {

    fun load() = execute {
        viewState =
            Loaded(detailPresenter.getUser())
    }

    fun saveUser(user: User, password: String?, photoUri: Uri?) = execute {
        viewState = Loading
        detailPresenter.saveUser(user, password, photoUri)
        viewState = Loaded(detailPresenter.getUser())
    }

    fun getCocktail(id: Int): Cocktail {
        var cocktail: Cocktail = Cocktail(1,"","","","","","","","","","","","","","","","","","","","","")
        GlobalScope.launch {
            cocktail = webservice.getCocktailById(id)
        }
        return cocktail
    }

    companion object{
        val webservice by lazy {
            Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(RemoteServiceInterface::class.java)
        }
    }


}