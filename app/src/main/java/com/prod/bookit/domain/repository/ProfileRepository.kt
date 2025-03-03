package com.prod.bookit.domain.repository

import com.prod.bookit.domain.model.ProfileBookingModel
import com.prod.bookit.domain.model.UserProfile

interface ProfileRepository {
    suspend fun getProfile(): UserProfile

    suspend fun getBookings(): List<ProfileBookingModel>

    suspend fun deleteBooking(id: String)
}