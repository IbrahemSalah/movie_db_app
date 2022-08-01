package com.android.baseapp.data.database

import com.android.baseapp.data.model.MovieListResponse

class DbDataSourceImpl(private val appDatabase: AppDatabase) : DbDataSource {

    override suspend fun getMovieListFromDB() = appDatabase.movieAppDao().getMovieListFromDB()

    override suspend fun getMovieListCount() = appDatabase.movieAppDao().getMovieListCount()

    override suspend fun saveMovieList(movieList: MovieListResponse) = appDatabase.movieAppDao().saveMovieList(movieList)
}