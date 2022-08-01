package com.android.baseapp.di.modules

import com.android.baseapp.data.offline.Offline
import com.android.baseapp.data.offline.OfflineImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val offlineModule = module{
    single<Offline> { OfflineImpl(androidApplication()) }
}