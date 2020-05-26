package com.hnpper.cocktailapp.ui.detail

import android.net.Uri
import co.zsmb.rainbowcake.base.JobViewModel
import com.hnpper.cocktailapp.model.User
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

    /*
    fun load() = execute {
        viewState = Loaded(cocktail =
            webservice.getCocktailById(11007, "1")

        ))

    }

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(RemoteServiceInterface::class.java)
    }
    */
}