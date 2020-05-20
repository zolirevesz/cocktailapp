package com.hnpper.cocktailapp.ui.home

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

class HomeViewModel (private val cocktailRepository: CocktailRepository) : ViewModel() {
/*
    fun load() = execute {
        viewState = HomeLoaded(cocktailList = listOf(
            Cocktail(
                1,
                "tempCocktail",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            ),
            webservice.getCocktailById(11007, "1")
            ))

    }
*/
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(RemoteServiceInterface::class.java)
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is All Series Fragment"
    }
    val text: LiveData<String> = _text

    val list: LiveData<List<Cocktail>> = cocktailRepository.getCocktails() as LiveData<List<Cocktail>>

    fun add(cocktail: Cocktail) {
        cocktailRepository.addCocktail(cocktail)
    }

}