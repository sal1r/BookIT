package com.prod.bookit.presentation.state

sealed class AuthState {
    object Loading : AuthState()
    object Authorized : AuthState()
    object Unauthorized : AuthState()
    data class Error(val message: String) : AuthState()
}