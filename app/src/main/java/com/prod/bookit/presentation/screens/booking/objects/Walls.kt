package com.prod.bookit.presentation.screens.booking.objects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.prod.bookit.presentation.models.CoworkingDefaults

@Composable
fun VerticalWall(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(CoworkingDefaults.wallWidth)
            .background(MaterialTheme.colorScheme.onSurface)
    )
}

@Composable
fun HorizontalWall(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(CoworkingDefaults.wallWidth)
            .background(MaterialTheme.colorScheme.onSurface)
    )
}