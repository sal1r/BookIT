package com.prod.bookit.data.remote.api

import com.prod.bookit.data.remote.dto.booking.BookRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BookingApi {

    @POST("bookings")
    suspend fun book(@Body body: BookRequestDto): Response<Unit>
}