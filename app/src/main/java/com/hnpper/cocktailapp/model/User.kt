package com.hnpper.cocktailapp.model

import androidx.room.*
import com.hnpper.cocktailapp.data.Converters
import java.io.Serializable

@Entity
data class UserRoom (
    @PrimaryKey(autoGenerate = true)
    var id : String,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "password")
    val password : String,
    @ColumnInfo(name = "photo")
    var photoImageUrl: String?,
    @ColumnInfo(name = "fav_cocktails")
    @TypeConverters(Converters::class)
    val favCocktailsId : List<Int>
) : Serializable

data class User (
    var id : String,
    var name : String,
    var email: String,
    var password : String,
    var photoImageUrl: String?,
    var favCocktailsId : MutableList<Int>
) : Serializable