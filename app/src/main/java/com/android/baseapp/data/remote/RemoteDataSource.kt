package com.android.baseapp.data.remote

import com.android.baseapp.data.model.BaseAppResponse
import com.android.baseapp.data.model.MovieDetail
import com.android.baseapp.data.model.MovieListResponse
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getDiscoverMovieList(apiKey: String): Response<MovieListResponse>

       suspend fun getMovieDetail(movie_id: String, apiKey: String): Response<MovieDetail>


}