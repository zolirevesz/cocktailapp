package com.hnpper.cocktailapp.data.disk

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.GsonBuilder
import com.hnpper.cocktailapp.data.Converters
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.CocktailRoom
import com.hnpper.cocktailapp.model.ResponseList
import com.hnpper.cocktailapp.model.User
import com.hnpper.cocktailapp.remote.RemoteServiceInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

@Database(entities = [CocktailRoom::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CocktailRoomDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao
    companion object {
        // For Singleton instantiation
        @Volatile private var instance: CocktailRoomDatabase? = null

        val webservice by lazy {
            Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(RemoteServiceInterface::class.java)
        }

        fun getInstance(context: Context): CocktailRoomDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CocktailRoomDatabase {
            return Room.databaseBuilder(context, CocktailRoomDatabase::class.java, "cocktail_db")
                .allowMainThreadQueries()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        GlobalScope.launch(Dispatchers.IO) {
                            val responseList: ResponseList =
                                webservice.getList()
                            getInstance(context).cocktailDao().insertAll(
                                responseToCocktail(responseList)
                            )
                        }
                    }
                })
                .build()
        }

        private fun responseToCocktail(list: ResponseList): List<CocktailRoom> {
            var resultList: MutableList<CocktailRoom> = mutableListOf()
            for (item: Cocktail in list.cocktailList) {
                resultList.add(
                    CocktailRoom(item.idDrink, item.strDrink, item.strCategory as String, item.strAlcoholic as String, item.strGlass as String, item.strInstruction as String,
                    listOf(item.strIngredient1 as String, item.strIngredient2 as String, item.strIngredient3 as String, item.strIngredient4 as String, item.strIngredient5 as String, item.strIngredient6 as String, item.strIngredient7 as String, item.strIngredient8 as String,
                        item.strIngredient9 as String, item.strIngredient10 as String, item.strIngredient11 as String, item.strIngredient12 as String, item.strIngredient13 as String, item.strIngredient14 as String, item.strIngredient15 as String), item.strDrinkThumb as String)
                )
            }
            return resultList
        }
    }
}