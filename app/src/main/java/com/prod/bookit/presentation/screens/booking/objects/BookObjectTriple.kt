package com.prod.bookit.presentation.screens.booking.objects

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.prod.bookit.presentation.models.BookObjectColors
import com.prod.bookit.presentation.models.BookObjectDefaults
import com.prod.bookit.presentation.models.BookObjectUIData
import com.prod.bookit.presentation.theme.DarkBlueTheme
import com.prod.bookit.presentation.theme.LightBlueTheme

@Composable
fun BookObjectTriple(
    bookObjectUIData: BookObjectUIData,
    ltr: Boolean = true,
    bookObjectColors: BookObjectColors = BookObjectDefaults.bookObjectColors,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    onClick: () -> Unit = {}
) {
    val containerColor = remember(bookObjectColors, bookObjectUIData) {
        if (bookObjectUIData.avalibleToBook) bookObjectColors.avalibleContainerColor
        else bookObjectColors.unavalibleContainerColor
    }

    CompositionLocalProvider(
        LocalContentColor provides
                if (bookObjectUIData.avalibleToBook) bookObjectColors.avalibleContentColor
                else bookObjectColors.unavalibleContentColor
    ) {
        Box {
            Box(
                modifier = Modifier.clickable(onClick = onClick)
            ) {
                Box(
                    modifier = Modifier
                        .align(if (ltr) Alignment.BottomStart else Alignment.BottomEnd)
                        .padding(bottom = BookObjectDefaults.cellSize)
                        .width(BookObjectDefaults.cellSize)
                        .height(BookObjectDefaults.cellSize + BookObjectDefaults.spaceSize)
                        .clip(MaterialTheme.shapes.medium.copy(
                            bottomEnd = CornerSize(0.dp),
                            bottomStart = CornerSize(0.dp)
                        ))
                        .background(containerColor)
                ) {
                    Box(
                        modifier = Modifier.size(BookObjectDefaults.cellSize)
                    ) {
                        Text(
                            text = bookObjectUIData.index.toString(),
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .zIndex(1f)
                        .align(Alignment.BottomCenter)
                ) {
                    Box(
                        modifier = Modifier
                            .height(BookObjectDefaults.cellSize)
                            .width(BookObjectDefaults.cellSize)
                            .clip(MaterialTheme.shapes.medium.copy(
                                topStart = if (ltr) CornerSize(0.dp) else MaterialTheme.shapes.medium.topStart,
                                topEnd = CornerSize(0.dp),
                                bottomEnd = CornerSize(0.dp)
                            ))
                            .background(containerColor)
                    )
                    Box(
                        modifier = Modifier
                            .height(BookObjectDefaults.cellSize)
                            .width(BookObjectDefaults.cellSize + BookObjectDefaults.spaceSize)
                            .clip(MaterialTheme.shapes.medium.copy(
                                topStart = CornerSize(0.dp),
                                bottomStart = CornerSize(0.dp),
                                topEnd = if (ltr) MaterialTheme.shapes.medium.topEnd else CornerSize(0.dp)
                            ))
                            .background(containerColor)
                    )
                }

                Box(
                    modifier = Modifier
                        .align(if (ltr) Alignment.TopEnd else Alignment.TopStart)
                        .size(BookObjectDefaults.cellSize + BookObjectDefaults.spaceSize)
                ) {
                    Box(
                        modifier = Modifier
                            .align(if (ltr) Alignment.BottomStart else Alignment.BottomEnd)
                            .size(BookObjectDefaults.cellSize / 2)
                            .background(containerColor)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .zIndex(1f)
                    .size(BookObjectDefaults.cellSize + BookObjectDefaults.spaceSize)
                    .align(if (ltr) Alignment.TopEnd else Alignment.TopStart)
                    .clip(MaterialTheme.shapes.medium)
                    .background(backgroundColor)
            )
        }
    }
}


@Composable
private fun BookObjectTriplePreview() {
    Surface {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BookObjectTriple(
                bookObjectUIData = BookObjectUIData(
                    index = 1,
                    avalibleToBook = true
                ),
                ltr = true
            )
            BookObjectTriple(
                bookObjectUIData = BookObjectUIData(
                    index = 2,
                    avalibleToBook = false
                ),
                ltr = false
            )
        }
    }
}

@Preview
@Composable
private fun BookObjectTripleDarkPreview() {
    MaterialTheme(
        colorScheme = DarkBlueTheme
    ) {
        BookObjectTriplePreview()
    }
}

@Preview
@Composable
private fun BookObjectTripleLightPreview() {
    MaterialTheme(
        colorScheme = LightBlueTheme
    ) {
        BookObjectTriplePreview()
    }
}