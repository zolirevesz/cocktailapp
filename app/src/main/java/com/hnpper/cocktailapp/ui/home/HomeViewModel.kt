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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homePresenter: HomePresenter
) : JobViewModel<HomeViewState>(Loading) {

    fun loadData() = execute {
        viewState = Loading
        if (homePresenter.existActiveUser()) {
            viewState = HomeLoaded(homePresenter.getUser())
        } else {
            viewState = HomeWithoutLogin
        }

    }

    fun logout() = execute {
        viewState = Loading
        if (homePresenter.logout()) {
            viewState = LoggedOut
        }
    }
}