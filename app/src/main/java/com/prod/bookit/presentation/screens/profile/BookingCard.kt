package com.prod.bookit.presentation.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prod.bookit.domain.model.ProfileBookingModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingCard(
    booking: ProfileBookingModel,
    onDelete: () -> Unit,
    onReschedule: () -> Unit,
    onShowQr: (ProfileBookingModel) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Место ${booking.title}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = booking.address,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = formatEventDateTime(booking.timeFrom, booking.timeUntil),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            if (booking.status == "active") {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                onShowQr(booking)
                            }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More options"
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Удалить") },
                                onClick = {
                                    expanded = false
                                    onDelete()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Перенести") },
                                onClick = {
                                    expanded = false
                                    onReschedule()
                                }
                            )
                        }
                    }
                }
            } else {
                Text(
                    text = "Завершено",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}

fun formatEventDateTime(startTime: String, endTime: String): String {
    val startDateTime = ZonedDateTime.parse(startTime)
    val endDateTime = ZonedDateTime.parse(endTime)

    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val date = startDateTime.format(dateFormatter)

    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val startTimeFormatted = startDateTime.format(timeFormatter)
    val endTimeFormatted = endDateTime.format(timeFormatter)

    return "$date с $startTimeFormatted до $endTimeFormatted"
}

@Preview
@Composable
private fun BookingCardDarkPreview() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
       // BookingCard()
    }
}

@Preview
@Composable
private fun BookingCardLightPreview() {
    MaterialTheme(
        colorScheme = lightColorScheme()
    ) {
        //BookingCard()
    }
}

