package com.prod.bookit.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.prod.bookit.domain.repository.BookingRepository
import com.prod.bookit.presentation.models.BookingData
import com.prod.bookit.presentation.models.BookingStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class BookingViewModel(
    private val bookingRepository: BookingRepository
): ViewModel() {

    fun book(bookingData: BookingData): Flow<BookingStatus> = flow {
        emit(BookingStatus.LOADING)

        val isBooked = bookingRepository.book(
            spotId = bookingData.spotId,
            timeFrom = bookingData.startTime,
            timeUntil = bookingData.endTime,
            date = bookingData.date
        )

        emit(if (isBooked) BookingStatus.SUCCESS else BookingStatus.ERROR)
    }
}