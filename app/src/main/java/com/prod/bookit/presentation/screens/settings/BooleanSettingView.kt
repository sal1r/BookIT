package com.prod.bookit.presentation.screens.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.prod.bookit.presentation.models.BooleanSetting

@Composable
fun BooleanSettingView(
    setting: BooleanSetting,
    settingName: String,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val value by setting.value.collectAsStateWithLifecycle()

    BooleanSettingViewContent(
        value = value ?: false,
        onValueChange = {
            coroutineScope.launch(Dispatchers.IO) {
                setting.save(it)
            }
        },
        settingName = settingName,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooleanSettingViewContent(
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    settingName: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(
            LocalRippleConfiguration provides null
        ) {
            Switch(
                checked = value,
                onCheckedChange = onValueChange
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = settingName,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun BooleanSettingViewPreview() {
    var checked by remember { mutableStateOf(false) }

    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        Surface {
            BooleanSettingViewContent(
                value = checked,
                onValueChange = { checked = it },
                settingName = "Test"
            )
        }
    }
}