package com.prod.bookit.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val LightBlueTheme = ColorScheme(
    primary = Color(0xFF1976D2),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFBBDEFB),
    onPrimaryContainer = Color(0xFF003366),
    inversePrimary = Color(0xFFE3F2FD),

    secondary = Color(0xFF5D4037),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD7CCC8),
    onSecondaryContainer = Color(0xFF2E1A10),
    inverseSurface = Color(0xFF303030),

    tertiary = Color(0xFF455A64),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFCFD8DC),
    onTertiaryContainer = Color(0xFF263238),

    background = Color(0xFFF5F5F5),
    onBackground = Color(0xFF212121),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF212121),

    surfaceVariant = Color(0xFFEEEEEE),
    onSurfaceVariant = Color(0xFF424242),
    surfaceTint = Color(0xFF1976D2),
    inverseOnSurface = Color(0xFFE0E0E0),

    error = Color(0xFFD32F2F),
    onError = Color.White,
    errorContainer = Color(0xFFFBE9E7),
    onErrorContainer = Color(0xFF4E1C15),

    outline = Color(0xFFBDBDBD),
    outlineVariant = Color(0xFF616161),
    scrim = Color(0x99000000),

    surfaceBright = Color(0xFFFAFAFA),
    surfaceDim = Color(0xFFE0E0E0),
    surfaceContainer = Color(0xFFF8F8F8),
    surfaceContainerHigh = Color(0xFFFAFAFA),
    surfaceContainerHighest = Color(0xFFF5F5F5),
    surfaceContainerLow = Color(0xFFFCFCFC),
    surfaceContainerLowest = Color(0xFFFFFFFF)
)

val DarkBlueTheme = ColorScheme(
    primary = Color(0xFF64B5F6),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF1976D2),
    onPrimaryContainer = Color(0xFFE3F2FD),
    inversePrimary = Color(0xFF0D47A1),

    secondary = Color(0xFFA1887F),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF5D4037),
    onSecondaryContainer = Color(0xFFD7CCC8),
    inverseSurface = Color(0xFFE0E0E0),

    tertiary = Color(0xFF78909C),
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFF455A64),
    onTertiaryContainer = Color(0xFFCFD8DC),

    background = Color(0xFF121212),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFFFFFFF),

    surfaceVariant = Color(0xFF424242),
    onSurfaceVariant = Color(0xFFBDBDBD),
    surfaceTint = Color(0xFF64B5F6),
    inverseOnSurface = Color(0xFF303030),

    error = Color(0xFFEF5350),
    onError = Color.Black,
    errorContainer = Color(0xFF5D1714),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFF616161),
    outlineVariant = Color(0xFFBDBDBD),
    scrim = Color(0x99FFFFFF),

    surfaceBright = Color(0xFF4F4F4F),
    surfaceDim = Color(0xFF262626),
    surfaceContainer = Color(0xFF262626),
    surfaceContainerHigh = Color(0xFF303030),
    surfaceContainerHighest = Color(0xFF3A3A3A),
    surfaceContainerLow = Color(0xFF2D2D2D),
    surfaceContainerLowest = Color(0xFF1A1A1A)
)

@Composable
fun FinalDraftForProdTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkBlueTheme
        else -> LightBlueTheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}