package com.prod.bookit.domain.repository

import android.net.Uri

interface AuthRepository {
    suspend fun register(email: String, password: String, fullName: String): Boolean
    suspend fun login(email: String, password: String): Boolean
    suspend fun signInWithYandex(token: String): Boolean
    suspend fun sendDeviceToken()

    fun getFcmToken(): String
    fun saveFcmToken(token: String)

    fun saveToken(token: String)
    fun getToken(): String?
    fun clearToken()

    suspend fun loadImage(uri: Uri): String
}