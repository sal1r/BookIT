package com.prod.finaldraftforprod.presentation.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.finaldraftforprod.presentation.models.BooleanSetting
import com.prod.finaldraftforprod.presentation.models.EnumSetting
import com.prod.finaldraftforprod.presentation.models.LangSetting
import com.prod.finaldraftforprod.presentation.models.ThemeSetting
import com.prod.finaldraftforprod.common.AppDispatchers
import com.prod.finaldraftforprod.domain.repository.SettingsRepository
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val appDispatchers: AppDispatchers,
    private val app: Application,
    private val settingsRepository: SettingsRepository
): ViewModel() {

    val themeSetting = EnumSetting(
        name = "theme",
        defaultValue = ThemeSetting.SYSTEM,
        repository = settingsRepository
    )

    val langSetting = EnumSetting(
        name = "lang",
        defaultValue = LangSetting.SYSTEM,
        repository = settingsRepository
    )

    val dcSetting = BooleanSetting(
        name = "dc",
        defaultValue = false,
        repository = settingsRepository
    )

    init {
        viewModelScope.launch(appDispatchers.io) {
            themeSetting.load()
            langSetting.load()
            dcSetting.load()
        }
    }
}