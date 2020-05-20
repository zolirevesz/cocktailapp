package com.hnpper.cocktailapp.data.disk

import com.hnpper.cocktailapp.model.Cocktail

class CocktailRepository private constructor(private val cocktailDao: CocktailDao) {
    fun getCocktails() = cocktailDao.getAll()

    fun getCocktail(idDrink: Int) = cocktailDao.getCocktailById(idDrink)

    fun addCocktail(cocktail: Cocktail) = cocktailDao.insert(cocktail)

    fun updateCocktail(cocktail: Cocktail) = cocktailDao.updateCocktail(cocktail)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: CocktailRepository? = null

        fun getInstance(cocktailDao: CocktailDao) =
            instance ?: synchronized(this) {
                instance ?: CocktailRepository(cocktailDao).also { instance = it }
            }
    }
}