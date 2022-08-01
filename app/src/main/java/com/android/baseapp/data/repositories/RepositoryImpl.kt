package com.android.baseapp.data.repositories

import com.android.baseapp.data.database.DbDataSource
import com.android.baseapp.data.model.*
import com.android.baseapp.data.offline.Offline
import com.android.baseapp.data.raw.RawDataSource
import com.android.baseapp.data.remote.RemoteDataSource
import com.android.baseapp.data.sharedpref.PrefDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RepositoryImp(
    private val remoteDataSource: RemoteDataSource,
    private val prefDataSource: PrefDataSource,
    private val offline: Offline,
    private val dbDataSource: DbDataSource,
    private val rawDataSource: RawDataSource
) : Repository {

    // ----------pref------------
    override fun getToken() = prefDataSource.getToken()

    override fun setToken(token: String) = prefDataSource.setToken(token)


   override fun getCachingTime() = prefDataSource.getCachingTime()

    override fun setCachingTime(cachingDate: Long) = prefDataSource.setCachingTime(cachingDate)

    override fun getSharedPrefBaseAppResponse() = prefDataSource.getSharedPrefBaseAppResponse()

    override fun setSharedPrefBaseAppResponse(baseAppResponse: BaseAppResponse) =
        prefDataSource.setSharedPrefBaseAppResponse(baseAppResponse)

    override fun logOut() = prefDataSource.logOut()


    // ---------- end pref------------

    // ----------raw------------
    override suspend fun getLocalBaseAppResponse() = withContext(Dispatchers.IO) {
        rawDataSource.getLocalBaseAppResponse()
    }

    // ----------end raw------------


    // ----------remote------------

    override suspend fun getDiscoverMovieList() =
        safeAPIResult(remoteDataSource.getDiscoverMovieList(prefDataSource.getToken()))

    override suspend fun getMovieDetail(movie_id: String) =
        safeAPIResult(remoteDataSource.getMovieDetail(movie_id, prefDataSource.getToken()))

    // ---------- end remote------------

    // ---------- offline------------

    override fun dummyOffline() = offline.dummyOffline()

    // ---------- end offline------------

    // ---------- database------------

    override suspend fun getMovieListFromDB() = dbDataSource.getMovieListFromDB()

    override suspend fun getMovieListCount() = dbDataSource.getMovieListCount()

    override suspend fun saveMovieList(movieList: MovieListResponse) = dbDataSource.saveMovieList(movieList)
    // ---------- end database------------


    private fun <T> safeAPIResult(response: Response<T>): APIResult<T> {
        return when {
            response.code() in 200..299 -> {
                APIResult.Success(response.body())
            }
            response.code() == 401 -> {
                throw  FailureException.InvalidUserException("", 401)
            }
            else -> {
                APIResult.Failure(APIError(response.raw().message, "", "", response.code()))
            }
        }
    }
}