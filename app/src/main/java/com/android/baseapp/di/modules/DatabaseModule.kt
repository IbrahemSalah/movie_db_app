package com.android.baseapp.di.modules

import com.android.baseapp.data.database.AppDatabase
import com.android.baseapp.data.database.DbDataSource
import com.android.baseapp.data.database.DbDataSourceImpl

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single { AppDatabase.getInstance(androidContext()) }

    single<DbDataSource> { DbDataSourceImpl(get()) }
}