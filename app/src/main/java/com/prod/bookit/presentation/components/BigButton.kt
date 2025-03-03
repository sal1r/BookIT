package com.prod.bookit.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BigButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isChosen: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    content: @Composable RowScope.() -> Unit
) {
    if (isChosen) {
        Button(
            onClick = onClick,
            modifier = modifier.height(48.dp),
            shape = MaterialTheme.shapes.medium,
            enabled = enabled,
            content = content,
            colors = colors
        )
    } else {
        OutlinedBigButton(
            onClick = onClick,
            modifier = modifier.height(48.dp),
            enabled = enabled,
            content = content,
            isChosen = false
        )
    }
}


@Composable
private fun BigButtonPreview() {
    Surface {
        BigButton(
            onClick = {},
            isChosen = true
        ) {
            Text("Big button")
        }
    }
}

@Preview
@Composable
private fun BigButtonDarkPreview() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        BigButtonPreview()
    }
}

@Preview
@Composable
private fun BigButtonLightPreview() {
    MaterialTheme(
        colorScheme = lightColorScheme()
    ) {
        BigButtonPreview()
    }
}