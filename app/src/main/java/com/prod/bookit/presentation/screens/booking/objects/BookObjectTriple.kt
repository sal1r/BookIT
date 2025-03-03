package com.prod.bookit.presentation.screens.booking.objects

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.prod.bookit.presentation.models.BookObjectColors
import com.prod.bookit.presentation.models.BookObjectDefaults
import com.prod.bookit.domain.model.BookObjectUIData
import com.prod.bookit.presentation.models.CoworkingDefaults
import com.prod.bookit.presentation.theme.DarkBlueTheme
import com.prod.bookit.presentation.theme.LightBlueTheme

@Composable
fun BookObjectTriple(
    bookObjectUIData: BookObjectUIData,
    modifier: Modifier = Modifier,
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
        Box(modifier = modifier) {
            Box(
                modifier =
                    if (bookObjectUIData.avalibleToBook) Modifier.clickable(onClick = onClick)
                    else Modifier
            ) {
                Box(
                    modifier = Modifier
                        .align(if (ltr) Alignment.BottomStart else Alignment.BottomEnd)
                        .padding(bottom = CoworkingDefaults.cellSize)
                        .requiredWidth(CoworkingDefaults.cellSize)
                        .requiredHeight(CoworkingDefaults.cellSize + CoworkingDefaults.spaceSize)
                        .clip(MaterialTheme.shapes.medium.copy(
                            bottomEnd = CornerSize(0.dp),
                            bottomStart = CornerSize(0.dp)
                        ))
                        .background(containerColor)
                ) {
                    Box(
                        modifier = Modifier.requiredSize(CoworkingDefaults.cellSize)
                    ) {
                        Text(
                            text = bookObjectUIData.position.toString(),
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
                            .requiredSize(CoworkingDefaults.cellSize)
                            .clip(MaterialTheme.shapes.medium.copy(
                                topStart = if (ltr) CornerSize(0.dp) else MaterialTheme.shapes.medium.topStart,
                                topEnd = CornerSize(0.dp),
                                bottomEnd = CornerSize(0.dp)
                            ))
                            .background(containerColor)
                    )
                    Box(
                        modifier = Modifier
                            .requiredHeight(CoworkingDefaults.cellSize)
                            .requiredWidth(CoworkingDefaults.cellSize + CoworkingDefaults.spaceSize)
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
                        .requiredSize(CoworkingDefaults.cellSize + CoworkingDefaults.spaceSize)
                ) {
                    Box(
                        modifier = Modifier
                            .align(if (ltr) Alignment.BottomStart else Alignment.BottomEnd)
                            .requiredSize(CoworkingDefaults.cellSize / 2)
                            .background(containerColor)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .zIndex(1f)
                    .requiredSize(CoworkingDefaults.cellSize + CoworkingDefaults.spaceSize)
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
                    position = 1,
                    avalibleToBook = true,
                    id = ""
                ),
                ltr = true
            )
            BookObjectTriple(
                bookObjectUIData = BookObjectUIData(
                    position = 2,
                    avalibleToBook = false,
                    id = ""
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