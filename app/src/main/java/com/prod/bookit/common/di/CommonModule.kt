package com.prod.bookit.common.di

import android.content.Context
import com.prod.bookit.common.AppDispatchers
import com.prod.bookit.common.MyFirebaseMessagingService
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val commonModule = module {

    single<AppDispatchers> {
        AppDispatchers(
            ui = Dispatchers.Main,
            io = Dispatchers.IO,
            default = Dispatchers.Default,
            unconfined = Dispatchers.Unconfined
        )
    }

    single { MyFirebaseMessagingService(get()) }

    single {
        androidContext().getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    }
}