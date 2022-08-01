package com.android.baseapp.data.database

import com.android.baseapp.data.model.MovieListResponse


interface DbDataSource {
    suspend fun getMovieListFromDB(): MovieListResponse

    suspend fun getMovieListCount(): Int

    suspend fun saveMovieList(movieList: MovieListResponse)
}