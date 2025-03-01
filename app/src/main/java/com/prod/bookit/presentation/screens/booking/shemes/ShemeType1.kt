package com.prod.bookit.presentation.screens.booking.shemes

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prod.bookit.presentation.models.BookObjectDefaults
import com.prod.bookit.presentation.models.BookObjectUIData
import com.prod.bookit.presentation.models.CoworkingDefaults
import com.prod.bookit.presentation.screens.booking.objects.BookObjectDouble
import com.prod.bookit.presentation.screens.booking.objects.BookObjectQuad
import com.prod.bookit.presentation.screens.booking.objects.BookObjectSingle
import com.prod.bookit.presentation.screens.booking.objects.BookObjectTriple
import com.prod.bookit.presentation.screens.booking.objects.HorizontalWall
import com.prod.bookit.presentation.screens.booking.objects.VerticalWall
import com.prod.bookit.presentation.theme.DarkBlueTheme
import com.prod.bookit.presentation.theme.LightBlueTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShemeType1(
    onBookingObjectClick: (index: Int) -> Unit = {},
    avalibleToBookObjects: List<Int> = List(28) { it + 1 }
) {
    Row {
        Column {
            for (i in 0..3) {
                FlowRow(
                    maxLines = 2,
                    verticalArrangement = Arrangement.spacedBy(CoworkingDefaults.spaceSize),
                    modifier = Modifier
                        .padding(24.dp)
                        .width(CoworkingDefaults.cellSize * 2 + CoworkingDefaults.spaceSize)
                ) {
                    BookObjectSingle(
                        bookObjectUIData = BookObjectUIData(
                            index = i * 3 + 1,
                            avalibleToBook = (i * 3 + 1) in avalibleToBookObjects
                        ),
                        onClick = { onBookingObjectClick(i * 3 + 1) }
                    )

                    Spacer(modifier = Modifier.width(CoworkingDefaults.spaceSize))

                    BookObjectSingle(
                        bookObjectUIData = BookObjectUIData(
                            index = i * 3 + 2,
                            avalibleToBook = (i * 3 + 2) in avalibleToBookObjects
                        ),
                        onClick = { onBookingObjectClick(i * 3 + 2) }
                    )

                    BookObjectDouble(
                        bookObjectUIData = BookObjectUIData(
                            index = i * 3 + 3,
                            avalibleToBook = (i * 3 + 3) in avalibleToBookObjects
                        ),
                        onClick = { onBookingObjectClick(i * 3 + 3) }
                    )
                }

                HorizontalWall(modifier = Modifier.width(CoworkingDefaults.cellSize * 2 + CoworkingDefaults.spaceSize + 48.dp))
            }

            BookObjectQuad(
                modifier = Modifier.padding(24.dp).padding(top = 24.dp),
                bookObjectUIData = BookObjectUIData(
                    index = 25,
                    avalibleToBook = 25 in avalibleToBookObjects
                ),
                onClick = { onBookingObjectClick(25) }
            )

            BookObjectTriple(
                modifier = Modifier.padding(24.dp),
                bookObjectUIData = BookObjectUIData(
                    index = 26,
                    avalibleToBook = 26 in avalibleToBookObjects
                ),
                onClick = { onBookingObjectClick(26) },
                ltr = true
            )
        }

        VerticalWall(modifier = Modifier.height(
            (CoworkingDefaults.cellSize * 2 + CoworkingDefaults.spaceSize + 48.dp + CoworkingDefaults.wallWidth) * 4
        ))

        Spacer(modifier = Modifier.width(128.dp))

        VerticalWall(modifier = Modifier.height(
            (CoworkingDefaults.cellSize * 2 + CoworkingDefaults.spaceSize + 48.dp + CoworkingDefaults.wallWidth) * 4
        ))

        Column {
            for (i in 0..3) {
                FlowRow(
                    maxLines = 2,
                    verticalArrangement = Arrangement.spacedBy(CoworkingDefaults.spaceSize),
                    modifier = Modifier
                        .padding(24.dp)
                        .width(CoworkingDefaults.cellSize * 2 + CoworkingDefaults.spaceSize)
                ) {
                    BookObjectSingle(
                        bookObjectUIData = BookObjectUIData(
                            index = i * 3 + 13,
                            avalibleToBook = (i * 3 + 13) in avalibleToBookObjects
                        ),
                        onClick = { onBookingObjectClick(i * 3 + 13) }
                    )

                    Spacer(modifier = Modifier.width(CoworkingDefaults.spaceSize))

                    BookObjectSingle(
                        bookObjectUIData = BookObjectUIData(
                            index = i * 3 + 14,
                            avalibleToBook = (i * 3 + 14) in avalibleToBookObjects
                        ),
                        onClick = { onBookingObjectClick(i * 3 + 14) }
                    )

                    BookObjectDouble(
                        bookObjectUIData = BookObjectUIData(
                            index = i * 3 + 15,
                            avalibleToBook = (i * 3 + 15) in avalibleToBookObjects
                        ),
                        onClick = { onBookingObjectClick(i * 3 + 15) }
                    )
                }

                HorizontalWall(modifier = Modifier.width(CoworkingDefaults.cellSize * 2 + CoworkingDefaults.spaceSize + 48.dp))
            }

            BookObjectQuad(
                modifier = Modifier.padding(24.dp).padding(top = 24.dp),
                bookObjectUIData = BookObjectUIData(
                    index = 27,
                    avalibleToBook = 27 in avalibleToBookObjects
                ),
                onClick = { onBookingObjectClick(27) }
            )

            BookObjectTriple(
                modifier = Modifier.padding(24.dp),
                bookObjectUIData = BookObjectUIData(
                    index = 28,
                    avalibleToBook = 28 in avalibleToBookObjects
                ),
                onClick = { onBookingObjectClick(28) },
                ltr = false
            )
        }
    }
}


@Composable
private fun ShemeType1Preview() {
    Surface {
        ShemeType1()
    }
}

@Preview(device = "spec:width=500dp,height=2000dp,dpi=440")
@Composable
private fun ShemeType1DarkPreview() {
    MaterialTheme(
        colorScheme = DarkBlueTheme
    ) {
        ShemeType1Preview()
    }
}

@Preview(device = "spec:width=500dp,height=2000dp,dpi=440")
@Composable
private fun ShemeType1LightPreview() {
    MaterialTheme(
        colorScheme = LightBlueTheme
    ) {
        ShemeType1Preview()
    }
}