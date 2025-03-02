package com.prod.bookit.data.repository

import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import androidx.core.net.toFile
import com.prod.bookit.common.AppDispatchers
import com.prod.bookit.data.remote.api.AuthApi
import com.prod.bookit.data.remote.dto.auth.AuthResponse
import com.prod.bookit.data.remote.dto.auth.LoginRequestDto
import com.prod.bookit.data.remote.dto.auth.NotificationsRequestDto
import com.prod.bookit.data.remote.dto.auth.RegisterRequestDto
import com.prod.bookit.data.remote.dto.auth.YandexToken
import com.prod.bookit.domain.repository.AuthRepository
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val prefs: SharedPreferences,
    private val dispatchers: AppDispatchers
) : AuthRepository {
    override suspend fun register(email: String, password: String, fullName: String): Boolean {
        val response = api.register(
            RegisterRequestDto(
                email = email,
                fullName = fullName,
                password = password
            )
        )

        val loginResponse = api.login(LoginRequestDto(email, password))
        val result = handleAuthResponse(loginResponse)

        sendDeviceToken()

        return result
    }

    override suspend fun login(email: String, password: String): Boolean {
        val response = api.login(LoginRequestDto(email, password))
        val result = handleAuthResponse(response)

        sendDeviceToken()
        return result
    }

    override suspend fun signInWithYandex(token: String): Boolean {
        val response = api.signInWithYandex(YandexToken(token))

        val result = handleAuthResponse(response)
        sendDeviceToken()

        return result
    }

    override suspend fun sendDeviceToken() {
        val token = getFcmToken()

        api.sendDeviceToken(
            NotificationsRequestDto(
                token,
                "android"
            )
        )
    }

    override fun getFcmToken(): String {
        val token = prefs.getString("fcm_token", null)

        if (token != null) {
            return token
        } else {
            throw IllegalStateException("null FCM token")
        }
    }

    override fun saveFcmToken(token: String) {
        prefs.edit().putString("fcm_token", token).apply()
    }

    private fun handleAuthResponse(response: AuthResponse): Boolean {
        saveToken(response.token)

        return true
    }

    override fun saveToken(token: String) {
        prefs.edit().putString("jwt_token", token).apply()
    }

    override fun getToken(): String? {
        return prefs.getString("jwt_token", null)
    }

    override fun clearToken() {
        prefs.edit().remove("jwt_token").apply()
    }

    override suspend fun loadImage(uri: Uri): String = withContext(dispatchers.io) {
        val file = uri.toFile()
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())

        api.uploadFile(
            file = MultipartBody.Part.createFormData(
                "file",
                file.name,
                requestFile
            )
        ).url
    }
}