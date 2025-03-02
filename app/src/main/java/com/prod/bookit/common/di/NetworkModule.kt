package com.prod.bookit.common.di

import com.prod.bookit.common.ApiConstants
import com.prod.bookit.data.remote.api.CoworkingsApi
import com.prod.bookit.data.remote.api.AuthApi
import com.prod.bookit.data.remote.api.BookingApi
import com.prod.bookit.data.remote.api.ProfileApi
import com.prod.bookit.data.remote.api.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { AuthInterceptor(get()) }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ProfileApi::class.java) }

    single { get<Retrofit>().create(AuthApi::class.java) }

    single { get<Retrofit>().create(CoworkingsApi::class.java) }

    single { get<Retrofit>().create(BookingApi::class.java) }
}