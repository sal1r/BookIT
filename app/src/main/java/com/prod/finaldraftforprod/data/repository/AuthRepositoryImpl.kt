package com.prod.finaldraftforprod.data.repository

import android.content.SharedPreferences
import com.prod.finaldraftforprod.data.remote.api.AuthApi
import com.prod.finaldraftforprod.data.remote.dto.auth.AuthResponse
import com.prod.finaldraftforprod.data.remote.dto.auth.LoginRequestDto
import com.prod.finaldraftforprod.data.remote.dto.auth.RegisterRequestDto
import com.prod.finaldraftforprod.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val prefs: SharedPreferences
) : AuthRepository {
    override suspend fun register(email: String, password: String): Boolean {
        val response = api.register(RegisterRequestDto(email, password))
        return handleAuthResponse(response)
    }

    override suspend fun login(email: String, password: String): Boolean {
        val response = api.login(LoginRequestDto(email, password))
        return handleAuthResponse(response)
    }

    override suspend fun signInWithYandex(token: String): Boolean {
        val response = api.signInWithYandex(token)
        return handleAuthResponse(response)
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
}