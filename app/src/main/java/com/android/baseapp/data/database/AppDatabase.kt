package com.android.baseapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.baseapp.data.database.typeConverter.DateTypeConverter
import com.android.baseapp.data.database.typeConverter.MovieListTypeConverter
import com.android.baseapp.data.database.typeConverter.MovieTypeConverter
import com.android.baseapp.data.model.MovieListResponse

@Database(
    entities = [MovieListResponse::class], version = 1
)
@TypeConverters(
    MovieListTypeConverter::class,
    MovieTypeConverter::class,
    DateTypeConverter::class
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun movieAppDao(): MovieAppDao

    companion object {
        private const val DB_NAME = "movie_app.db"

        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}