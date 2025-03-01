package com.prod.finaldraftforprod.presentation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.prod.finaldraftforprod.presentation.models.LangSetting
import com.prod.finaldraftforprod.presentation.models.ThemeSetting
import com.prod.finaldraftforprod.presentation.viewModels.SettingsViewModel
import com.prod.finaldraftforprod.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    rootNavController: NavHostController,
    settingsViewModel: SettingsViewModel = koinViewModel()
) {
    SettingsScreenContent(
        onBackClick = {
            rootNavController.popBackStack()
        },
        settings = {
            SettingsBlock(
                name = stringResource(R.string.settings__block_general)
            ) {
                EnumSettingView(
                    setting = settingsViewModel.langSetting,
                    settingName = stringResource(R.string.settings__title_lang)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            SettingsBlock(
                name = stringResource(R.string.settings__block_appearance)
            ) {
                BooleanSettingView(
                    setting = settingsViewModel.dcSetting,
                    settingName = stringResource(R.string.settings__tilte_dc)
                )

                Spacer(modifier = Modifier.height(8.dp))

                EnumSettingView(
                    setting = settingsViewModel.themeSetting,
                    settingName = stringResource(R.string.settings__title_theme)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScreenContent(
    onBackClick: () -> Unit = {},
    settings: @Composable ColumnScope.() -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.settings__title))
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable(
                            onClick = onBackClick,
                            interactionSource = null,
                            indication = null
                        ),
                        painter = painterResource(R.drawable.ic_arrow_back_24),
                        contentDescription = null
                    )
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Column {
                settings()
            }
        }
    }
}

@Composable
private fun SettingsScreenPreview() {
    SettingsScreenContent(
        settings = {
            SettingsBlock(
                name = "General"
            ) {
                EnumSettingViewContent(
                    settingName = "Language",
                    selected = LangSetting.SYSTEM,
                    items = LangSetting.entries.toTypedArray(),
                    onSelect = {}
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            SettingsBlock(
                name = "Appearance"
            ) {
                BooleanSettingViewContent(
                    value = true,
                    onValueChange = {},
                    settingName = "Dynamic colors"
                )

                Spacer(Modifier.height(8.dp))

                EnumSettingViewContent(
                    settingName = "Theme",
                    selected = ThemeSetting.SYSTEM,
                    items = ThemeSetting.entries.toTypedArray(),
                    onSelect = {}
                )
            }
        }
    )
}

@Preview
@Composable
private fun SettingsScreenDarkPreview() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        SettingsScreenPreview()
    }
}

@Preview
@Composable
private fun SettingsScreenLightPreview() {
    MaterialTheme(
        colorScheme = lightColorScheme()
    ) {
        SettingsScreenPreview()
    }
}