package com.android.baseapp.util


import java.util.*

object DateTimeUtil {

    fun getCurrentDateTime(): Long {
        val currentDateAsLong = Calendar.getInstance().time
        return currentDateAsLong.time
    }

    fun isExpired(cachingTime: Long): Boolean {
        val currentDateAsLong = Calendar.getInstance().time.time
        val cachingDuration :Long = currentDateAsLong - cachingTime
        return cachingDuration > Constants.FOUR_HOURS_IN_MILLI_SECONDS
    }
}