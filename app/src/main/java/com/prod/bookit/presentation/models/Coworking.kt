package com.prod.bookit.presentation.models

import kotlinx.serialization.Serializable

@Serializable
data class Coworking(
    val id: String,
    val name: String
) {
    companion object {
        val coworkings = listOf(
            Coworking(
                id = "c6158cf4-853f-44f2-b755-904b6781c179",
                name = "Big-Space Белорусская"
            ),
            Coworking(
                id = "a1b2c3d4-e5f6-7890-1234-567890abcdef",
                name = "Y-Space Технопарк"
            )
        )
    }
}