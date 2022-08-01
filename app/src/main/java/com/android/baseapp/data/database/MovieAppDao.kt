package com.android.baseapp.data.database

import androidx.room.*
import com.android.baseapp.data.model.MovieListResponse

@Dao
interface MovieAppDao {

    @Query("SELECT * FROM movie_table")
    fun getMovieListFromDB(): MovieListResponse

    @Query("SELECT COUNT(*) FROM movie_table")
    fun getMovieListCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieList(movieList: MovieListResponse)
}