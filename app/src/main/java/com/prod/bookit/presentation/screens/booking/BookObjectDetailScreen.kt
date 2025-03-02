package com.prod.bookit.presentation.screens.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prod.bookit.R
import com.prod.bookit.presentation.components.BigButton
import com.prod.bookit.presentation.components.OutlinedBigButton
import com.prod.bookit.presentation.models.BookingData
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookObjectDetailScreen(
    onDismiss: () -> Unit,
    bookingData: BookingData,
    isLoading: Boolean = false,
    onBookClick: (BookingData) -> Unit = {}
) {
    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        onDismissRequest = onDismiss,
        contentWindowInsets = { WindowInsets.systemBars },
        modifier = Modifier.fillMaxSize()
    ) {
        BookObjectDetailScreenContent(
            isLoading = isLoading,
            bookingData = bookingData,
            onBookClick = { onBookClick(bookingData) },
            onChangeClick = onDismiss
        )
    }
}

@Composable
private fun BookObjectDetailScreenContent(
    bookingData: BookingData,
    isLoading: Boolean = false,
    onChangeClick: () -> Unit = {},
    onBookClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.booking__coworking_title, bookingData.coworkingName),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.booking__place_title, bookingData.bookObjectIndex),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.booking__date, bookingData.date.format(
                DateTimeFormatter.ofPattern("dd MMMM")
            )),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(
                R.string.booking__from_until,
                bookingData.startTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                bookingData.endTime.format(DateTimeFormatter.ofPattern("HH:mm"))
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        Row {
            OutlinedBigButton(
                modifier = Modifier.weight(1f),
                onClick = onChangeClick
            ) {
                Text(
                    text = stringResource(R.string.booking__change)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            BigButton(
                modifier = Modifier.weight(1f),
                enabled = !isLoading,
                onClick = onBookClick
            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(
                        text = stringResource(R.string.booking__book)
                    )
                }
            }
        }
    }
}


@Composable
private fun BookObjectDetailScreenPreview() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        BookObjectDetailScreenContent(
            BookingData(
                coworkingId = 1,
                coworkingName = "т-ворк",
                bookObjectIndex = 26,
                startTime = LocalTime.of(16, 30),
                endTime = LocalTime.of(18, 0),
                date = LocalDate.of(2025, 3, 12)
            )
        )
    }
}

@Preview
@Composable
private fun BookObjectDetailScreenDarkPreview() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        BookObjectDetailScreenPreview()
    }
}

@Preview
@Composable
private fun BookObjectDetailScreenLightPreview() {
    MaterialTheme(
        colorScheme = lightColorScheme()
    ) {
        BookObjectDetailScreenPreview()
    }
}