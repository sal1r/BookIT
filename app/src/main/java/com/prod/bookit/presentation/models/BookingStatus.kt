package com.prod.bookit.presentation.models

import com.prod.bookit.domain.model.BookObjectUIData

sealed class BookingStatus {

    data object Empty : BookingStatus()

    data object Loading : BookingStatus()

    data object Success : BookingStatus()

    data class Error(val bookObjectUIData: BookObjectUIData?) : BookingStatus()
}
