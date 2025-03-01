package com.prod.bookit.data.remote.api

import com.prod.bookit.data.remote.dto.CoworkingDetailDto
import com.prod.bookit.data.remote.dto.CoworkingSummaryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoworkingsApi {

    @GET("coworkings")
    suspend fun getAllCoworkings(): List<CoworkingSummaryDto>

    @GET("coworkings/{id}")
    suspend fun getCoworkingById(
        @Path("id") id: String
    ): CoworkingDetailDto
}