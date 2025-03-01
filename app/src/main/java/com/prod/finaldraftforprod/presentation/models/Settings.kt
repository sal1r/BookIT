package com.prod.finaldraftforprod.presentation.models

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import com.prod.finaldraftforprod.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Stable
interface Setting<T> {
    val name: String
    val defaultValue: T
    val value: StateFlow<T?>
    suspend fun save(value: T)
    suspend fun load()
}

@Stable
class EnumSetting<T : Enum<T>>(
    override val name : String,
    override val defaultValue: T,
    private val repository: SettingsRepository
) : Setting<T> {
    private val _value: MutableStateFlow<T?> = MutableStateFlow(null)
    override val value: StateFlow<T?> = _value

    override suspend fun save(value: T) {
        repository.saveString(name, value.name)
        _value.value = value
    }

    override suspend fun load() {
        _value.value = repository.getString(name, defaultValue.name).let { data ->
            defaultValue::class.java.enumConstants.firstOrNull { it.name == data }
        }
    }
}

@Stable
class BooleanSetting(
    override val name : String,
    override val defaultValue: Boolean,
    private val repository: SettingsRepository
) : Setting<Boolean> {
    private val _value: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    override val value: StateFlow<Boolean?> = _value

    override suspend fun save(value: Boolean) {
        repository.saveBoolean(name, value)
        _value.value = value
    }

    override suspend fun load() {
        _value.value = repository.getBoolean(name, defaultValue)
    }
}

@Stable
class IntSetting(
    override val name : String,
    override val defaultValue: Int,
    private val repository: SettingsRepository
) : Setting<Int> {
    private val _value: MutableStateFlow<Int?> = MutableStateFlow(null)
    override val value: StateFlow<Int?> = _value

    override suspend fun save(value: Int) {
        repository.saveInt(name, value)
        _value.value = value
    }

    override suspend fun load() {
        _value.value = repository.getInt(name, defaultValue)
    }
}

@Stable
class StringSetting(
    override val name : String,
    override val defaultValue: String,
    private val repository: SettingsRepository
) : Setting<String> {
    private val _value: MutableStateFlow<String?> = MutableStateFlow(null)
    override val value: StateFlow<String?> = _value

    override suspend fun save(value: String) {
        repository.saveString(name, value)
        _value.value = value
    }

    override suspend fun load() {
        _value.value = repository.getString(name, defaultValue)
    }
}

interface SettingEnum {
    @get:StringRes val text: Int
}