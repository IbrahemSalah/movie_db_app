package com.android.baseapp.ui.landing.movielistscreen

import com.android.baseapp.data.model.MovieListResponse

sealed class MovieListResponseState{
    data class Success(val response: MovieListResponse) : MovieListResponseState()
    data class Failure(val error: String) : MovieListResponseState()
    data class Loading(val loading: Boolean) : MovieListResponseState()

}
