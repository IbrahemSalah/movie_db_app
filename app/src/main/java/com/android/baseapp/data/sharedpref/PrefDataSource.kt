package com.android.baseapp.data.sharedpref

import com.android.baseapp.data.model.BaseAppResponse


interface PrefDataSource {

    fun getToken(): String
    fun setToken(token: String)

    fun getCachingTime(): Long
    fun setCachingTime(cachingDate: Long)

    fun getSharedPrefBaseAppResponse(): BaseAppResponse?
    fun setSharedPrefBaseAppResponse(baseAppResponse: BaseAppResponse)

    fun logOut()
}