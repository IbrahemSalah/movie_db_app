package com.android.baseapp.di.modules

import com.google.gson.Gson
import com.android.baseapp.data.raw.RawDataSource
import com.android.baseapp.data.raw.RawDataSourceImp
import com.android.baseapp.data.remote.RemoteDataSource
import com.android.baseapp.data.remote.RemoteDataSourceImp
import com.android.baseapp.data.sharedpref.PrefDataSource
import com.android.baseapp.data.sharedpref.PrefDataSourceImp
import com.android.baseapp.data.repositories.Repository
import com.android.baseapp.data.repositories.RepositoryImp
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val repositoryModule = module {
    single<RemoteDataSource> {
        return@single RemoteDataSourceImp(get())
    }
    single<RawDataSource> { RawDataSourceImp(androidContext(), Gson()) }
    single<PrefDataSource> { PrefDataSourceImp(androidApplication()) }

    single<Repository> {
        RepositoryImp(
            remoteDataSource = get(),
            prefDataSource = get(),
            offline = get(),
            dbDataSource = get(),
            rawDataSource = get()
        )
    }
}
