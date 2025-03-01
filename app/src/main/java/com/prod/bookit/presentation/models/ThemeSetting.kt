package com.prod.bookit.presentation.models

import androidx.annotation.StringRes
import com.prod.bookit.R

enum class ThemeSetting(
    @StringRes override val text: Int
): SettingEnum {
    DARK(R.string.settings__theme_dark),
    LIGHT(R.string.settings__theme_light),
    SYSTEM(R.string.settings__theme_system)
}