package com.hnpper.cocktailapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class Converters {
    var gson = Gson()

    @TypeConverter
    fun stringToIntegerList(data: String?): List<Int?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<Int?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun integerListToString(someObjects: List<Int?>?): String? {
        return gson.toJson(someObjects)
    }
}