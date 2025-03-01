package com.prod.bookit.domain.model

data class CoworkingDetail(
    val id: String,
    val name: String,
    val description: String,
    val address: String,
    val capacity: Int,
    val opensAt: String,
    val endsAt: String,
    val images: List<String>
)
