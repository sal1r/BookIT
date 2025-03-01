package com.prod.bookit.presentation.screens.home

import kotlinx.serialization.Serializable

sealed class HomeNavDestinations {

    @Serializable
    object Main : HomeNavDestinations()

    @Serializable
    object Screen2 : HomeNavDestinations()

    @Serializable
    object Screen3 : HomeNavDestinations()
}