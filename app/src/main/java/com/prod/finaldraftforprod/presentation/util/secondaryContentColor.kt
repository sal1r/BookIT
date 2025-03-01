package com.prod.finaldraftforprod.presentation.util

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

val Color.secondary: Color
    inline get() = copy(alpha = 0.7f)

val secondaryContentColor
    @ReadOnlyComposable
    @Composable
    inline get() = LocalContentColor.current.secondary

