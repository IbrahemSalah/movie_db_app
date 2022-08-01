package com.android.baseapp.ui.landing.moviedetailscreen

import androidx.lifecycle.viewModelScope
import com.android.baseapp.data.model.APIResult
import com.android.baseapp.data.repositories.Repository
import com.android.baseapp.ui.base.BaseViewModel
import com.android.baseapp.ui.landing.movielistscreen.MovieListResponseState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel (
    private val repository: Repository
) : BaseViewModel() {

    private var _movieDetailResponseState = Channel<MovieDetailResponseState>(Channel.BUFFERED)
    val movieDetailResponseState get() = _movieDetailResponseState.receiveAsFlow()


    fun getMovieDetail(movieId:String) {

        viewModelScope.launch(handler) {
            _movieDetailResponseState.offer(MovieDetailResponseState.Loading(true))

            when (val response = repository.getMovieDetail(movieId)) {
                is APIResult.Failure -> {
                    _movieDetailResponseState.offer(MovieDetailResponseState.Loading(false))
                    response.error?.let {
                        _movieDetailResponseState.offer(MovieDetailResponseState.Failure(response.error.message))

                    }
                }
                is APIResult.Success -> {
                    _movieDetailResponseState.offer(MovieDetailResponseState.Loading(false))
                    response.body?.let {
                        _movieDetailResponseState.offer(MovieDetailResponseState.Success(response.body))
                    }
                }
            }
        }

    }
}