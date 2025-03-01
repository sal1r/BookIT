package com.prod.bookit.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.bookit.domain.model.CoworkingDetail
import com.prod.bookit.domain.model.CoworkingSummary
import com.prod.bookit.domain.repository.CoworkingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoworkingsViewModel(
    private val repository: CoworkingsRepository
): ViewModel() {
    private val _coworkings = MutableStateFlow<List<CoworkingSummary>>(emptyList())
    val coworkings: StateFlow<List<CoworkingSummary>> = _coworkings

    private val _selectedDetail = MutableStateFlow<CoworkingDetail?>(null)
    val selectedDetail: StateFlow<CoworkingDetail?> = _selectedDetail

    fun loadCoworkings() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _coworkings.value = repository.getAllCoworkings()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadCoworkingDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val detail = repository.getCoworkingDetail(id)
                _selectedDetail.value = detail
            } catch (e: Exception) {
                e.printStackTrace()
                _selectedDetail.value = CoworkingDetail(
                    id = "0",
                    name = "Коворкинг",
                    description = "На нашем пути было много прегпад и я готов заявить что мы их все преодолели",
                    capacity = 500,
                    address = "ул. Домашняя д.5",
                    opensAt = "10:00",
                    endsAt = "20:00",
                    images = listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRX--aELMY2GsaOOGdA-ZDSPcKpxBZoU6LmUBq3DixDdWX8UXLjWEQC3hsHF-P1grOdj-Q&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRX--aELMY2GsaOOGdA-ZDSPcKpxBZoU6LmUBq3DixDdWX8UXLjWEQC3hsHF-P1grOdj-Q&usqp=CAU")
                )
            }
        }
    }

    fun clearSelectedDetail() {
        _selectedDetail.value = null
    }
}