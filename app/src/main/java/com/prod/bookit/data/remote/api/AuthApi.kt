package com.prod.bookit.data.remote.api

import com.prod.bookit.data.remote.dto.auth.AuthResponse
import com.prod.bookit.data.remote.dto.auth.FileUploadResponse
import com.prod.bookit.data.remote.dto.auth.LoginRequestDto
import com.prod.bookit.data.remote.dto.auth.NotificationsRequestDto
import com.prod.bookit.data.remote.dto.auth.RegisterRequestDto
import okhttp3.MultipartBody
import com.prod.bookit.data.remote.dto.auth.YandexToken
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AuthApi {
    @POST("users")
    suspend fun register(@Body request: RegisterRequestDto)

    @Multipart
    @POST("storage/upload")
    suspend fun uploadFile(
        @Part file: MultipartBody.Part
    ): FileUploadResponse

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): AuthResponse

    @POST("auth/login/yandex")
    suspend fun signInWithYandex(@Body token: YandexToken): AuthResponse

    @POST("notifications/device-tokens")
    suspend fun sendDeviceToken(@Body info: NotificationsRequestDto)
}