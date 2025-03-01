package com.prod.bookit.common

import android.app.Application
import com.prod.bookit.common.di.commonModule
import com.prod.bookit.common.di.networkModule
import com.prod.bookit.common.di.repositoryModule
import com.prod.bookit.common.di.roomModule
import com.prod.bookit.common.di.useCaseModule
import com.prod.bookit.common.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

val appModule = listOf(
    viewModelModule,
    networkModule,
    repositoryModule,
    useCaseModule,
    roomModule,
    commonModule
)

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}