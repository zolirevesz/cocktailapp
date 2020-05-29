package com.hnpper.cocktailapp.ui.detail

import android.net.Uri
import co.zsmb.rainbowcake.withIOContext
import com.google.gson.GsonBuilder
import com.hnpper.cocktailapp.interactors.FirebaseInteractor
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.ResponseList
import com.hnpper.cocktailapp.model.User
import com.hnpper.cocktailapp.remote.RemoteServiceInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val firebaseInteractor: FirebaseInteractor) {

    suspend fun getUser(): User = withIOContext {
        firebaseInteractor.getUser()
    }

    suspend fun saveUser(user: User, password: String?, photoUri: Uri?): Boolean = withIOContext {
        firebaseInteractor.saveUser(user, password, photoUri)
    }

    companion object {
        val webservice by lazy {
            Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(RemoteServiceInterface::class.java)
        }
    }

    suspend fun getCocktailById(id : Int): Cocktail = withIOContext {
        webservice.getCocktailById(id).drinks[0]
    }
}