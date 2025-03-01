package com.prod.bookit.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.bookit.domain.repository.AuthRepository
import com.prod.bookit.presentation.state.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthorized)
    val authState: StateFlow<AuthState> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            try {
                val success = authRepository.login(email, password)
                _authState.value = if (success) AuthState.Authorized else AuthState.Error("Error")
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Error")
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            try {
                val success = authRepository.register(email, password)
                _authState.value = if (success) AuthState.Authorized else AuthState.Error("Error")
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Error")
            }
        }
    }

    fun signInWithYandex(token: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            try {
                val success = authRepository.signInWithYandex(token)
                _authState.value = if (success) AuthState.Authorized else AuthState.Error("Error")
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Error")
            }
        }
    }

    fun logout() {
        authRepository.clearToken()
        _authState.value = AuthState.Unauthorized
    }
}