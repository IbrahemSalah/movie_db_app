package com.android.baseapp.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSourceImp(private val remoteApi: BaseAppAPI) : RemoteDataSource {

    override suspend fun getDiscoverMovieList(apiKey: String) = withContext(Dispatchers.IO) {
        remoteApi.getDiscoverMovieList(apiKey)
    }

    override suspend fun getMovieDetail(movie_id: String, apiKey: String) =
        withContext(Dispatchers.IO) {
            remoteApi.getMovieDetail(movie_id, apiKey)
        }
}