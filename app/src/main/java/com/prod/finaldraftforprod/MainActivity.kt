package com.prod.finaldraftforprod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.prod.finaldraftforprod.presentation.screens.RootNavigation
import com.prod.finaldraftforprod.presentation.theme.FinalDraftForProdTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalDraftForProdTheme {
                KoinAndroidContext {
                    RootNavigation()
                }
            }
        }
    }
}