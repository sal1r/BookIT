package com.prod.bookit.common.di

import com.prod.bookit.data.remote.api.AuthApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://*.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(AuthApi::class.java) }
}