package com.prod.bookit.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.bookit.domain.model.BookingModel
import com.prod.bookit.domain.model.UserProfile
import com.prod.bookit.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
): ViewModel() {
    private val _profile = MutableStateFlow<UserProfile?>(null)
    val profile: StateFlow<UserProfile?> = _profile

    private val _bookings = MutableStateFlow<List<BookingModel>>(emptyList())
    val bookings: StateFlow<List<BookingModel>> = _bookings

    fun loadProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getProfile().let {
                    _profile.value = it
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteBooking(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteBooking(id)
                loadBookings()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadBookings() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _bookings.value = repository.getBookings()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}