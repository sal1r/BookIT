package com.prod.bookit.data.remote.api.interceptors

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val prefs: SharedPreferences
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (originalRequest.url.encodedPath.startsWith("/users") ||
            originalRequest.url.encodedPath.startsWith("/auth")) {

            val newRequest = originalRequest.newBuilder().apply {
                header("accept", "application/json")
                header("Content-Type", "application/json")
            }.build()

            return chain.proceed(newRequest)
        }

        val token = prefs.getString("jwt_token", null)

        val newRequest = originalRequest.newBuilder().apply {
            header("accept", "application/json")
            header("Content-Type", "application/json")
            token?.let {
                header("Authorization", "Bearer $it")
            }
        }.build()

        return chain.proceed(newRequest)
    }
}