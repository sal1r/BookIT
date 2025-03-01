package com.prod.finaldraftforprod.presentation.models

import androidx.annotation.StringRes
import com.prod.finaldraftforprod.R
import com.prod.finaldraftforprod.presentation.models.SettingEnum

enum class ThemeSetting(
    @StringRes override val text: Int
): SettingEnum {
    DARK(R.string.settings__theme_dark),
    LIGHT(R.string.settings__theme_light),
    SYSTEM(R.string.settings__theme_system)
}