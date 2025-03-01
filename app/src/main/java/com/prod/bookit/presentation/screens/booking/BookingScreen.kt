package com.prod.bookit.presentation.screens.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prod.bookit.R
import com.prod.bookit.presentation.components.BigButton
import com.prod.bookit.presentation.models.Coworking
import com.prod.bookit.presentation.theme.DarkBlueTheme
import com.prod.bookit.presentation.theme.LightBlueTheme

@Composable
fun BookingScreen() {
    BookingScreenContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookingScreenContent(
    onBackClick: () -> Unit = {},
    onInfoClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
             CenterAlignedTopAppBar(
                 navigationIcon = {
                     IconButton(onClick = onBackClick) {
                         Icon(
                             painter = painterResource(R.drawable.ic_arrow_back_24),
                             contentDescription = null
                         )
                     }
                 },
                 title = {
                     Text(
                         text = stringResource(
                             R.string.booking__coworking_title, "т-ворк"
                         ),
                         modifier = Modifier.basicMarquee(Int.MAX_VALUE)
                     )
                 },
                 actions = {
                     IconButton(onClick = onInfoClick) {
                         Icon(
                             painter = painterResource(R.drawable.ic_info_outlined_24),
                             contentDescription = null
                         )
                     }
                 }
             )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Column {
                Box(

                ) {

                }

                Row(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(vertical = 16.dp)
                ) {
                    BigButton(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        )
                    ) {
                        Text("Дата")
                    }
                    
                    Spacer(modifier = Modifier.width(16.dp))

                    BigButton(
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Забронировать")
                    }
                }
            }
        }
    }
}


@Composable
private fun BookingScreenPreview() {
    BookingScreenContent()
}

@Preview
@Composable
private fun BookingScreenDarkPreview() {
    MaterialTheme(
        colorScheme = DarkBlueTheme
    ) {
        BookingScreenPreview()
    }
}

@Preview
@Composable
private fun BookingScreenLightPreview() {
    MaterialTheme(
        colorScheme = LightBlueTheme
    ) {
        BookingScreenPreview()
    }
}