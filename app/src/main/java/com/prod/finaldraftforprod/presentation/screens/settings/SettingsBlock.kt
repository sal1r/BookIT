package com.prod.finaldraftforprod.presentation.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingsBlock(
    name: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        ) {
            content()
        }
    }
}

@Composable
private fun SettingsBlockPreview() {
    SettingsBlock(
        name = "General",
        content = {}
    )
}

@Preview
@Composable
private fun SettingsBlockDarkPreview() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        SettingsBlockPreview()
    }
}

@Preview
@Composable
private fun SettingsBlockLightPreview() {
    MaterialTheme(
        colorScheme = lightColorScheme()
    ) {
        SettingsBlockPreview()
    }
}