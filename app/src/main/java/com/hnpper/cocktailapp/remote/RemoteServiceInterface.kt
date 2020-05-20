package com.hnpper.cocktailapp.remote

import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.ResponseList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//Test API key is: 1

interface RemoteServiceInterface {
    @GET("search.php?s={strDrink}")
    suspend fun getCocktails(@Path("strDrink") name: String, @Query("api_key") apikey: String): ResponseList

    @GET("lookup.php?i={idDrink}")
    suspend fun getCocktailById(@Path("idDrink") id: Int, @Query("api_key") apikey: String): Cocktail

    @GET("search.php?f=a")
    suspend fun getList(@Query("api_key") apikey: String): ResponseList
}