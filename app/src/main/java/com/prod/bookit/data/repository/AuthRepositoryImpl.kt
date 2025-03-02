package com.prod.bookit.data.repository

import android.content.Context
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
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val prefs: SharedPreferences,
    private val dispatchers: AppDispatchers,
    private val context: Context
) : AuthRepository {
    override suspend fun register(email: String, password: String, fullName: String, avatarUri: Uri?): Boolean {
        val avatarUrl = try {
            avatarUri?.let { loadImage(it) }
        } catch (e: Exception) { 
            Log.e("AuthRepositoryImpl", "Ошибка при загрузке аватара", e)
            null 
        }

        val response = api.register(
            RegisterRequestDto(
                email = email,
                fullName = fullName,
                password = password,
                avatarUrl = avatarUrl
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

    private suspend fun loadImage(uri: Uri): String? = withContext(dispatchers.io) {
        try {
            Log.d("AuthRepositoryImpl", "Loading image from URI: $uri")

            val tempFile = File.createTempFile("avatar_", ".jpg")

            Log.d("AuthRepositoryImpl", "Created temp file: ${tempFile.absolutePath}")

            val inputStream = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(tempFile)

            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            Log.d("AuthRepositoryImpl", "Copied content to temp file, size: ${tempFile.length()}")

            return@withContext uploadFileToServer(tempFile)
        } catch (e: Exception) {
            Log.e("AuthRepositoryImpl", "Error loading image", e)
            return@withContext null
        }
    }
    
    private suspend fun uploadFileToServer(file: File): String {
        Log.d("AuthRepositoryImpl", "Uploading file: ${file.absolutePath}, size: ${file.length()}")
        
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        
        val response = api.uploadFile(
            file = MultipartBody.Part.createFormData(
                "file",
                file.name,
                requestFile
            )
        )
        
        Log.d("AuthRepositoryImpl", "Upload successful, received URL: ${response.url}")
        
        return response.url
    }
}