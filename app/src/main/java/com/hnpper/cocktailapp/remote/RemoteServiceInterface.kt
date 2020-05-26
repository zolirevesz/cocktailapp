package com.hnpper.cocktailapp.remote

import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.ResponseList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//Test API key is: 1

interface RemoteServiceInterface {
    @GET("search.php")
    suspend fun getCocktails(@Query("s") name: String): ResponseList

    @GET("lookup.php")
    suspend fun getCocktailById(@Query("i") id: Int): Cocktail

    @GET("search.php?f=a")
    suspend fun getList(): ResponseList
}