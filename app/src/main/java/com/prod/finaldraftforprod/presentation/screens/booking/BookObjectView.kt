package com.prod.finaldraftforprod.presentation.screens.booking

import android.content.ContentQueryMap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.tooling.preview.Preview
import com.prod.finaldraftforprod.presentation.models.BookObject
import com.prod.finaldraftforprod.presentation.models.Coworking
import kotlin.math.floor

fun DrawScope.BookObjectView(
    coworking: Coworking,
    bookObject: BookObject,
    bookingCanvasParams: BookingCanvasParams = BookingCanvasParams.default
) {
    bookObject.dots.forEach { dot ->
        val size = bookingCanvasParams.cellSize.toPx()
        val x = size * (dot % coworking.width)
        val y = size * floor(dot.toFloat() / coworking.width)

        val paddingSize = bookingCanvasParams.paddingSize.toPx()

        clipRect(
            left = x + if (bookObject.dots.any {
                    size * (it % coworking.width) > x &&
                            size * floor(dot.toFloat() / coworking.width) == y
                }
            ) paddingSize else 0f,
            right = x + size - if (bookObject.dots.any {
                    size * (it % coworking.width) < x &&
                            size * floor(dot.toFloat() / coworking.width) == y
                }
            ) paddingSize else 0f
        ) {
            drawRect(
                color = Color.Green,
                topLeft = Offset(
                    x = x,
                    y = y
                ),
                size = Size(size, size)
            )
        }
    }
}


@Composable
private fun BookObjectViewPreview() {
    Surface {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            for (x in 0..20) {
                drawLine(
                    color = Color.Magenta,
                    start = Offset(x.toFloat() * BookingCanvasParams.default.cellSize.toPx(), 0f),
                    end = Offset(x.toFloat(), size.height * BookingCanvasParams.default.cellSize.toPx()),
                    strokeWidth = 1f
                )
            }

            for (y in 0..20) {
                drawLine(
                    color = Color.Magenta,
                    start = Offset(0f, y.toFloat() * BookingCanvasParams.default.cellSize.toPx()),
                    end = Offset(size.width, y.toFloat() * BookingCanvasParams.default.cellSize.toPx()),
                    strokeWidth = 1f
                )
            }

            BookObjectView(
                bookObject = BookObject(
                    index = 1,
                    dots = listOf(1, 2, 5)
                ),
                coworking = Coworking(
                    id = 0,
                    name = "",
                    width = 4,
                    height = 3
                )
            )
        }
    }
}

@Preview
@Composable
private fun BookObjectViewDarkPreview() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        BookObjectViewPreview()
    }
}

@Preview
@Composable
private fun BookObjectViewLightPreview() {
    MaterialTheme(
        colorScheme = lightColorScheme()
    ) {
        BookObjectViewPreview()
    }
}