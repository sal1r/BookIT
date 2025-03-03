package com.prod.bookit.presentation.screens.booking.objects

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prod.bookit.presentation.models.BookObjectColors
import com.prod.bookit.presentation.models.BookObjectDefaults
import com.prod.bookit.domain.model.BookObjectUIData
import com.prod.bookit.presentation.models.CoworkingDefaults
import com.prod.bookit.presentation.theme.DarkBlueTheme
import com.prod.bookit.presentation.theme.LightBlueTheme

@Composable
fun BookObjectSingle(
    bookObjectUIData: BookObjectUIData,
    modifier: Modifier = Modifier,
    bookObjectColors: BookObjectColors = BookObjectDefaults.bookObjectColors,
    onClick: () -> Unit = {}
) {
    CompositionLocalProvider(
        LocalContentColor provides
                if (bookObjectUIData.avalibleToBook) bookObjectColors.avalibleContentColor
                else bookObjectColors.unavalibleContentColor
    ) {
        Box(
            modifier = modifier
                .requiredSize(CoworkingDefaults.cellSize)
                .clip(MaterialTheme.shapes.medium)
                .background(
                    if (bookObjectUIData.avalibleToBook) bookObjectColors.avalibleContainerColor
                    else bookObjectColors.unavalibleContainerColor
                )
                .then(
                    if (bookObjectUIData.avalibleToBook) Modifier.clickable(onClick = onClick)
                    else Modifier
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = bookObjectUIData.position.toString(),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}


@Composable
private fun BookObjectSinglePreview() {
    Surface {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BookObjectSingle(
                bookObjectUIData = BookObjectUIData(
                    position = 1,
                    avalibleToBook = true,
                    id = ""
                )
            )
            BookObjectSingle(
                bookObjectUIData = BookObjectUIData(
                    position = 2,
                    avalibleToBook = false,
                    id = ""
                )
            )
        }
    }
}

@Preview
@Composable
private fun BookObjectSingleDarkPreview() {
    MaterialTheme(
        colorScheme = DarkBlueTheme
    ) {
        BookObjectSinglePreview()
    }
}

@Preview
@Composable
private fun BookObjectSingleLightPreview() {
    MaterialTheme(
        colorScheme = LightBlueTheme
    ) {
        BookObjectSinglePreview()
    }
}