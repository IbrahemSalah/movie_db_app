package com.android.baseapp.data.database.typeConverter

import androidx.room.TypeConverter
import com.android.baseapp.data.model.MovieListResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieListTypeConverter {

    @TypeConverter
    fun fromMovieListResponse(value: MovieListResponse?): String? {
        if (value == null)
            return null
        val gson = Gson()
        val type = object : TypeToken<MovieListResponse>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toMovieListResponse(value: String?): MovieListResponse? {
        val gson = Gson()
        val type = object : TypeToken<MovieListResponse>() {}.type
        return gson.fromJson(value, type)
    }

}