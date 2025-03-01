package com.prod.bookit.presentation.screens.booking.objects

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prod.bookit.presentation.models.BookObjectColors
import com.prod.bookit.presentation.models.BookObjectDefaults
import com.prod.bookit.presentation.models.BookObjectUIData
import com.prod.bookit.presentation.models.CoworkingDefaults
import com.prod.bookit.presentation.theme.DarkBlueTheme
import com.prod.bookit.presentation.theme.LightBlueTheme

@Composable
fun BookObjectDouble(
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
                .requiredHeight(CoworkingDefaults.cellSize)
                .requiredWidth(CoworkingDefaults.cellSize * 2 + CoworkingDefaults.spaceSize)
                .clip(MaterialTheme.shapes.medium)
                .background(
                    if (bookObjectUIData.avalibleToBook) bookObjectColors.avalibleContainerColor
                    else bookObjectColors.unavalibleContainerColor
                )
                .clickable(onClick = onClick),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = bookObjectUIData.index.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier.width(CoworkingDefaults.cellSize),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}


@Composable
private fun BookObjectDoublePreview() {
    Surface {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BookObjectDouble(
                bookObjectUIData = BookObjectUIData(
                    index = 1,
                    avalibleToBook = true
                )
            )
            BookObjectDouble(
                bookObjectUIData = BookObjectUIData(
                    index = 2,
                    avalibleToBook = false
                )
            )
        }
    }
}

@Preview
@Composable
private fun BookObjectDoubleDarkPreview() {
    MaterialTheme(
        colorScheme = DarkBlueTheme
    ) {
        BookObjectDoublePreview()
    }
}

@Preview
@Composable
private fun BookObjectDoubleLightPreview() {
    MaterialTheme(
        colorScheme = LightBlueTheme
    ) {
        BookObjectDoublePreview()
    }
}