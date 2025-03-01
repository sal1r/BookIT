package com.prod.bookit.domain.repository

import com.prod.bookit.domain.model.CoworkingDetail
import com.prod.bookit.domain.model.CoworkingSummary

interface CoworkingsRepository {
    suspend fun getAllCoworkings(): List<CoworkingSummary>
    suspend fun getCoworkingDetail(id: String): CoworkingDetail
}