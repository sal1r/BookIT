package com.prod.bookit.presentation.screens.booking.shemes

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ShemeType1() {

}


@Composable
private fun ShemeType1Preview() {
    Surface {
        ShemeType1()
    }
}

@Preview
@Composable
private fun ShemeType1DarkPreview() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        ShemeType1Preview()
    }
}

@Preview
@Composable
private fun ShemeType1LightPreview() {
    MaterialTheme(
        colorScheme = lightColorScheme()
    ) {
        ShemeType1Preview()
    }
}