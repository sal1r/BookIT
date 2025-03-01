package com.prod.finaldraftforprod.presentation.components

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.calculateCentroid
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun ScalableBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.(scale: Float, offset: Offset) -> Unit
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, rotation ->
                    val newScale = scale + scale * (zoom - 1f)
                    val zoomOffset = (centroid - offset) * (1f - zoom)

                    scale = newScale
                    offset += pan + zoomOffset
                }

//                awaitEachGesture {
//                    while (true) {
//                        val event = awaitPointerEvent()
//
//                        if (event.changes.size < 2) continue
//
//                        event.changes.forEach { it.consume() }
//
//                        val zoom = event.calculateZoom()
//                        val centroid = event.calculateCentroid().let {
//                            if (it == Offset.Unspecified) Offset(0f, 0f)
//                            else it
//                        }
//                        val pan = event.calculatePan()
//
//                        val newScale = scale + scale * (zoom - 1f)
//                        val zoomOffset = (centroid - offset) * (1f - zoom)
//
//                        scale = newScale
//                        offset += pan + zoomOffset
//                    }
//                }
            },
        contentAlignment = contentAlignment,
        content = { content(scale, offset) }
    )
}