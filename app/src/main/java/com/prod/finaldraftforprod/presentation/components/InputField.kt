package com.prod.finaldraftforprod.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    value: String,
    label: String? = null,
    leadingIcon: ImageVector? = null,
    onValueChange: (String) -> Unit,
    error: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label?.let { { Text(text = it) } },
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        leadingIcon = leadingIcon?.let {
            { Icon(imageVector = it, contentDescription = null) }
        },
        isError = error != null,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        supportingText = error?.let {
            { Text(text = it, color = MaterialTheme.colorScheme.error) }
        }
    )
}

@Composable
private fun InputFieldPreview() {
    Surface {
        InputField(
            value = "value",
            label = "label",
            onValueChange = {}
        )
    }
}

@Preview
@Composable
private fun InputFieldDarkPreview() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        InputFieldPreview()
    }
}

@Preview
@Composable
private fun InputFieldLightPreview() {
    MaterialTheme(
        colorScheme = lightColorScheme()
    ) {
        InputFieldPreview()
    }
}