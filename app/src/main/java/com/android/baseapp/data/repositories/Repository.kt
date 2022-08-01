package com.android.baseapp.data.repositories


import com.android.baseapp.data.database.DbDataSource
import com.android.baseapp.data.model.APIResult
import com.android.baseapp.data.model.BaseAppResponse
import com.android.baseapp.data.model.MovieDetail
import com.android.baseapp.data.model.MovieListResponse
import com.android.baseapp.data.raw.RawDataSource
import com.android.baseapp.data.sharedpref.PrefDataSource

interface Repository : PrefDataSource, DbDataSource, RawDataSource {

    suspend fun getDiscoverMovieList(): APIResult<MovieListResponse>

    suspend fun getMovieDetail(movie_id: String): APIResult<MovieDetail>

    fun dummyOffline(): APIResult<String>
}
