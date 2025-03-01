package com.prod.finaldraftforprod.common

import android.app.Application
import com.prod.finaldraftforprod.common.di.commonModule
import com.prod.finaldraftforprod.common.di.networkModule
import com.prod.finaldraftforprod.common.di.repositoryModule
import com.prod.finaldraftforprod.common.di.roomModule
import com.prod.finaldraftforprod.common.di.useCaseModule
import com.prod.finaldraftforprod.common.di.viewModelModule
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