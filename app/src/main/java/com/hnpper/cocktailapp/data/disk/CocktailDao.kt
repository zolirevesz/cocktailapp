package com.hnpper.cocktailapp.data.disk

import androidx.room.*
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.User

@Dao
interface CocktailDao {
    @Insert
    fun insertAll(cocktailList : List<Cocktail>)

    @Insert
    fun insert(cocktail : Cocktail)

    @Query("SELECT * FROM Cocktail")
    fun getAll(): List<Cocktail>

    @Query("SELECT * FROM Cocktail WHERE idDrink = :id")
    fun getCocktailById(id : Int): Cocktail

    @Update
    fun updateCocktail(cocktail : Cocktail): Int

    @Delete
    fun delete(cocktail : Cocktail)
}