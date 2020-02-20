package com.example.cocktailapp.data

import androidx.room.Entity

@Entity
data class User (
    val username : String,
    val password : String,
    val favCocktails : List<Cocktail>
)