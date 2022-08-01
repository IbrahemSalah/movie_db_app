package com.android.baseapp.di.modules

import com.android.baseapp.data.SessionManager
import org.koin.dsl.module

val appModule = module {
    single { SessionManager.getInstance(get()) }
}