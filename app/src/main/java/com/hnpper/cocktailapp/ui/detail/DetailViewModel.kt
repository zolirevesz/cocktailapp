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

    lateinit var cocktail: Cocktail

    fun load() = execute {
        viewState =
            Loaded(detailPresenter.getUser())
    }

    fun saveUser(user: User, password: String?, photoUri: Uri?) = execute {
        viewState = Loading
        detailPresenter.saveUser(user, password, photoUri)
        viewState = Loaded(detailPresenter.getUser())
    }

    fun getCocktail(id: Int) = execute {
        viewState = Loading
        cocktail = detailPresenter.getCocktailById(id)
        viewState = Loaded(detailPresenter.getUser())
    }


}