package com.prod.bookit.presentation.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.prod.bookit.presentation.components.OutlinedBigButton

@Composable
fun RescheduleDialog(
    freeSlots: List<Pair<String, String>>,
    selectedStartTime: String,
    selectedEndTime: String,
    onStartTimeClick: () -> Unit,
    onEndTimeClick: () -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.Black),
            elevation = CardDefaults.elevatedCardElevation(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Перенести время",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                freeSlots.forEach { (start, end) ->
                    Text(
                        text = "Свободно с $start до $end",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Start time button (outlined, matching "Начало: 18:00")
                OutlinedBigButton(
                    onClick = onStartTimeClick,
                    enabled = true,
                    content = { Text("Начало: $selectedStartTime") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onEndTimeClick,
                    modifier = Modifier.height(48.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(contentColor = Color(0xFF1976D2))
                ) {
                    Text("Конец: $selectedEndTime", color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onConfirm,
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                ) {
                    Text("Выбрать", color = Color.White)
                }
            }
        }
    }
}

@Preview
@Composable
private fun RescheduleDialogScreenDarkPreview() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        //RescheduleDialogScreenContent()
    }
}

@Preview
@Composable
private fun RescheduleDialogScreenLightPreview() {
    MaterialTheme(
        colorScheme = lightColorScheme()
    ) {
       // RescheduleDialogScreenContent()
    }
}

