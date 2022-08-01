package com.android.baseapp.ui.landing.moviedetailscreen

import com.android.baseapp.data.model.MovieDetail


sealed class MovieDetailResponseState {
    data class Success(val response: MovieDetail) : MovieDetailResponseState()
    data class Failure(val error: String) : MovieDetailResponseState()
    data class Loading(val loading: Boolean) : MovieDetailResponseState()

}