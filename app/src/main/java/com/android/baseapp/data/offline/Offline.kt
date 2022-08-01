package com.android.baseapp.data.offline

import com.android.baseapp.data.model.APIResult

interface Offline {
    fun dummyOffline(): APIResult<String>
}