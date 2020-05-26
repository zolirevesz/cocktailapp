package com.hnpper.cocktailapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hnpper.cocktailapp.data.Converters
import java.io.Serializable

@Entity
data class CocktailRoom (
    @PrimaryKey(autoGenerate = true)
    var idDrink : Int,
    @ColumnInfo(name = "name")
    val strDrink : String,
    @ColumnInfo(name = "category")
    val strCategory : String,
    @ColumnInfo(name = "alcoholic")
    val strAlcoholic : String?,
    @ColumnInfo(name = "glass")
    val strGlass : String?,
    @ColumnInfo(name = "instruction")
    val strInstruction : String?,
    @ColumnInfo(name = "ingredients")
    @TypeConverters(Converters::class)
    val strIngredients : List<String>,
    @ColumnInfo(name = "img_url")
    val strDrinkThumb : String?
) : Serializable

data class Cocktail (
    val idDrink : Int,
    val strDrink : String,
    val strCategory : String?,
    val strAlcoholic : String?,
    val strGlass : String?,
    val strInstruction : String?,
    val strIngredient1 : String?,
    val strIngredient2 : String?,
    val strIngredient3 : String?,
    val strIngredient4 : String?,
    val strIngredient5 : String?,
    val strIngredient6 : String?,
    val strIngredient7 : String?,
    val strIngredient8 : String?,
    val strIngredient9 : String?,
    val strIngredient10 : String?,
    val strIngredient11 : String?,
    val strIngredient12 : String?,
    val strIngredient13 : String?,
    val strIngredient14 : String?,
    val strIngredient15 : String?,
    val strDrinkThumb : String?
)