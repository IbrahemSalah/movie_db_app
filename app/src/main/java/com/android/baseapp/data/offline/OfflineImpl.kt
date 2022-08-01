package com.android.baseapp.data.offline

import android.content.Context
import com.android.baseapp.data.model.APIResult

class OfflineImpl(private val context: Context) : Offline {

    override fun dummyOffline(): APIResult<String> {
        val dummyString = "dummy"
        return APIResult.success(dummyString)
    }
}