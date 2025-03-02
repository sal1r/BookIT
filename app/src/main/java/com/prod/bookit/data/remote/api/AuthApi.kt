package com.prod.bookit.data.remote.api

import com.prod.bookit.data.remote.dto.auth.AuthResponse
import com.prod.bookit.data.remote.dto.auth.LoginRequestDto
import com.prod.bookit.data.remote.dto.auth.NotificationsRequestDto
import com.prod.bookit.data.remote.dto.auth.RegisterRequestDto
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.HEAD
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("users")
    suspend fun register(@Body request: RegisterRequestDto)

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): AuthResponse

    @POST("auth/yandex")
    suspend fun signInWithYandex(@Body token: String): AuthResponse

    @POST("/notifications/device-tokens")
    suspend fun sendDeviceToken(@Body info: NotificationsRequestDto)
}