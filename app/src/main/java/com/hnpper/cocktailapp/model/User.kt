package com.hnpper.cocktailapp.model

import androidx.room.*
import com.hnpper.cocktailapp.data.Converters
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
    @TypeConverters(Converters::class)
    val favCocktailsId : List<Int>
) : Serializable