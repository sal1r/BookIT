package com.prod.bookit.data.remote.api

import com.prod.bookit.data.remote.dto.profile.ProfileBookingDto
import com.prod.bookit.data.remote.dto.profile.UserProfileDto
import retrofit2.http.GET

interface ProfileApi {

    @GET("users/me")
    suspend fun getProfile(): UserProfileDto

    @GET("users/me/bookings")
    suspend fun getBookings(): List<ProfileBookingDto>
}