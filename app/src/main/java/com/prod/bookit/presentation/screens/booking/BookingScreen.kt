package com.prod.bookit.presentation.screens.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prod.bookit.R
import com.prod.bookit.presentation.components.BigButton
import com.prod.bookit.presentation.components.ScalableBox
import com.prod.bookit.presentation.models.Coworking
import com.prod.bookit.presentation.models.CoworkingDefaults
import com.prod.bookit.presentation.screens.booking.shemes.ShemeType1
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
    onInfoClick: () -> Unit = {},
    onBookObjectClick: (index: Int) -> Unit = {}
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
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    ScalableBox(
                        modifier = Modifier.fillMaxSize().wrapContentSize(unbounded = true, align = Alignment.TopStart),
                        contentAlignment = Alignment.TopStart,
                        initialScale = maxWidth / ((CoworkingDefaults.cellSize * 2 + CoworkingDefaults.spaceSize + 48.dp + CoworkingDefaults.wallWidth) * 2 + 128.dp  + CoworkingDefaults.wallWidth * 2)
                    ) { scale, offset ->
                        ShemeType1(
                            onBookObjectClick = onBookObjectClick,
                            modifier = Modifier
                                .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                translationX = offset.x
                                translationY = offset.y
                                transformOrigin = TransformOrigin(0f, 0f)
                            }
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .align(Alignment.BottomCenter)
                            .background(Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.surface
                                )
                            ))
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .align(Alignment.TopCenter)
                            .background(Brush.verticalGradient(
                                listOf(
                                    MaterialTheme.colorScheme.surface,
                                    Color.Transparent
                                )
                            ))
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(4.dp)
                            .align(Alignment.CenterStart)
                            .background(Brush.horizontalGradient(
                                listOf(
                                    MaterialTheme.colorScheme.surface,
                                    Color.Transparent
                                )
                            ))
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(4.dp)
                            .align(Alignment.CenterEnd)
                            .background(Brush.horizontalGradient(
                                listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.surface
                                )
                            ))
                    )

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