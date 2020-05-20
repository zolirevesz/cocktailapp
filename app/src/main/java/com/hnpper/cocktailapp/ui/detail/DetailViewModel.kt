package com.hnpper.cocktailapp.ui.detail

import androidx.lifecycle.ViewModel
import co.zsmb.rainbowcake.base.JobViewModel
import com.google.gson.GsonBuilder
import com.hnpper.cocktailapp.data.disk.CocktailRepository
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.remote.RemoteServiceInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailViewModel (private val cocktailRepository: CocktailRepository, private val idDrink: Int): ViewModel() {
    /*fun load() = execute {
        viewState = Loaded(cocktail =
            webservice.getCocktailById(11007, "1")

        ))

    }*/

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(RemoteServiceInterface::class.java)
    }

    val cocktail = cocktailRepository.getCocktail(idDrink)

    fun add(cocktail: Cocktail) {
        cocktailRepository.addCocktail(cocktail)
    }

    fun update(cocktail: Cocktail) {
        cocktailRepository.updateCocktail(cocktail)
    }

    fun getImgUrl(): String {
        return cocktail.strDrinkThumb as String
    }
}