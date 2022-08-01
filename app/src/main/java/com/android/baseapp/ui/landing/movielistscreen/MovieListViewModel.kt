package com.android.baseapp.ui.landing.movielistscreen

import androidx.lifecycle.viewModelScope
import com.android.baseapp.data.model.APIResult
import com.android.baseapp.data.model.MovieListResponse
import com.android.baseapp.data.repositories.Repository
import com.android.baseapp.ui.base.BaseViewModel
import com.android.baseapp.util.Constants
import com.android.baseapp.util.DateTimeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repository: Repository
) : BaseViewModel() {

    private var _movieListResponseState = Channel<MovieListResponseState>(Channel.BUFFERED)
    val movieListResponseState get() = _movieListResponseState.receiveAsFlow()

    init {
        // assuming that we did the auth and we saved user token into encrypted shared pref
        repository.setToken(Constants.AUTH_TOKEN)

        //single data source and it will do the job getting it from server or DB
        getDiscoveryMovieList()
    }

    private fun getDiscoveryMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            //checking if data exists already or not
            val dbDataExist = repository.getMovieListCount()

            if (!DateTimeUtil.isExpired(repository.getCachingTime()) && dbDataExist > 0) {
                getDiscoveryMovieListFromDB()
            } else {
                getDiscoveryMovieListFromServer()
            }
        }
    }

    private fun getDiscoveryMovieListFromServer() {

        viewModelScope.launch(handler) {
            _movieListResponseState.offer(MovieListResponseState.Loading(true))

            when (val response = repository.getDiscoverMovieList()) {
                is APIResult.Failure -> {
                    _movieListResponseState.offer(MovieListResponseState.Loading(false))
                    response.error?.let {
                        // generic handling as it's only for demo
                        _movieListResponseState.offer(MovieListResponseState.Failure(response.error.message))

                    }
                }
                is APIResult.Success -> {
                    _movieListResponseState.offer(MovieListResponseState.Loading(false))
                    response.body?.let {
                        saveCachingTime()
                        saveMovieListToDB(response.body)
                        _movieListResponseState.offer(MovieListResponseState.Success(response.body))
                    }
                }
            }
        }

    }

    private fun getDiscoveryMovieListFromDB() {

        viewModelScope.launch(Dispatchers.IO) {
            _movieListResponseState.offer(MovieListResponseState.Loading(true))

            val response = repository.getMovieListFromDB()
            response.results?.let {
                if (response.results.isNotEmpty()) {
                    _movieListResponseState.offer(MovieListResponseState.Loading(false))
                    _movieListResponseState.offer(MovieListResponseState.Success(response))
                } else {
                    // generic handling as it's only for demo
                    _movieListResponseState.offer(MovieListResponseState.Loading(false))
                    _movieListResponseState.offer(MovieListResponseState.Failure("Something Went Wrong"))
                }
            }
        }
    }

    private fun saveMovieListToDB(movieList: MovieListResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveMovieList(movieList)
        }
    }

    private fun saveCachingTime() {
        repository.setCachingTime(DateTimeUtil.getCurrentDateTime())
    }
}