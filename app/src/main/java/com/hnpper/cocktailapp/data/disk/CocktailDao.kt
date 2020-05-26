package com.hnpper.cocktailapp.data.disk

import androidx.room.*
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.CocktailRoom
import com.hnpper.cocktailapp.model.User

@Dao
interface CocktailDao {
    @Insert
    fun insertAll(cocktailList : List<CocktailRoom>)

    @Insert
    fun insert(cocktail : CocktailRoom)

    @Query("SELECT * FROM CocktailRoom")
    fun getAll(): List<CocktailRoom>

    @Query("SELECT * FROM CocktailRoom WHERE idDrink = :id")
    fun getCocktailById(id : Int): CocktailRoom

    @Update
    fun updateCocktail(cocktail : CocktailRoom): Int

    @Delete
    fun delete(cocktail : CocktailRoom)
}