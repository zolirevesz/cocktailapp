package com.hnpper.cocktailapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    @ColumnInfo(name = "username")
    val username : String,
    @ColumnInfo(name = "password")
    val password : String,
    @ColumnInfo(name = "fav_cocktails")
    val favCocktails : List<Cocktail>
) : Serializable