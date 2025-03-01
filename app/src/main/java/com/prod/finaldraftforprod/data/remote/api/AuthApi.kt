package com.prod.finaldraftforprod.data.remote.api

import com.prod.finaldraftforprod.data.remote.dto.auth.AuthResponse
import com.prod.finaldraftforprod.data.remote.dto.auth.LoginRequestDto
import com.prod.finaldraftforprod.data.remote.dto.auth.RegisterRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequestDto): AuthResponse

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): AuthResponse

    @POST("auth/yandex")
    suspend fun signInWithYandex(@Body token: String): AuthResponse
}