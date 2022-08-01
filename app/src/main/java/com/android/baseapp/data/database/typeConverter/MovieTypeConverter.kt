package com.android.baseapp.data.database.typeConverter

import androidx.room.TypeConverter
import com.android.baseapp.data.model.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieTypeConverter {
    @TypeConverter
    fun fromMovie(value: List<Movie>?): String? {
        if (value == null)
            return null
        val gson = Gson()
        val type = object : TypeToken<List<Movie>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toMovie(value: String?): List<Movie>? {
        val gson = Gson()
        val type = object : TypeToken<List<Movie>>() {}.type
        return gson.fromJson(value, type)
    }

}