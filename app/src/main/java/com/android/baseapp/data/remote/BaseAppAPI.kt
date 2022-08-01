package com.android.baseapp.data.remote

import com.android.baseapp.data.model.BaseAppResponse
import com.android.baseapp.data.model.MovieDetail
import com.android.baseapp.data.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface BaseAppAPI {

    @GET("discover/movie")
    suspend fun getDiscoverMovieList(@Query("api_key") apiKey: String): Response<MovieListResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movie_id: String,
        @Query("api_key") apiKey: String
    ): Response<MovieDetail>


}