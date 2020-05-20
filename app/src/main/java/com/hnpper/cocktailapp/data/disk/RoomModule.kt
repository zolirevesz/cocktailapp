package com.hnpper.cocktailapp.data.disk

import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val DatabaseModule = module {
    single { provideRoomDatabase(androidContext()) }
    single { provideDatabaseDao(get()) }
    single { DiskDataSource(get()) }
}

fun provideRoomDatabase(context: Context): CocktailRoomDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        CocktailRoomDatabase::class.java,
        "cocktail_db")
        .fallbackToDestructiveMigration()
        .build()
}

fun provideDatabaseDao(cocktailRoomDatabase: CocktailRoomDatabase): CocktailDao {
    return cocktailRoomDatabase.cocktailDao()
}