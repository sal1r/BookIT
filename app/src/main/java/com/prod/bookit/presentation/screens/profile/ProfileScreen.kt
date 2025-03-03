package com.prod.bookit.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.prod.bookit.R
import com.prod.bookit.domain.model.ProfileBookingModel
import com.prod.bookit.presentation.components.QrDialog
import com.prod.bookit.presentation.screens.RootNavDestinations
import com.prod.bookit.presentation.viewModels.AuthViewModel
import com.prod.bookit.presentation.viewModels.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    authViewModel: AuthViewModel = koinViewModel(),
    rootNavController: NavController
) {
    val profile by viewModel.profile.collectAsState()
    val bookings by viewModel.bookings.collectAsState()
    val isLoaded by viewModel.isLoaded.collectAsState()

    var qrBooking by remember { mutableStateOf<ProfileBookingModel?>(null) }

    var rescheduleBooking by remember { mutableStateOf<ProfileBookingModel?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
        viewModel.loadBookings()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { rootNavController.navigateUp() }
                )

                Icon(
                    painter = painterResource(R.drawable.logout_24px),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .clickable {
                            authViewModel.logout()
                            rootNavController.navigate(RootNavDestinations.Welcome) {
                                popUpTo(RootNavDestinations.Welcome) {
                                    inclusive = true
                                }
                            }
                        }
                )
            }

            if (profile != null && isLoaded) {
                ProfileHeader(profile = profile!!)
            } else {
                Box {
                    CircularProgressIndicator()
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Ваши бронирования",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (bookings.isEmpty()) {
                Text("У вас пока нет бронирований.")
            } else {
                bookings.forEach { booking ->
                    BookingCard(
                        booking = booking,
                        onDelete = { viewModel.deleteBooking(booking.id) },
                        onReschedule = { rescheduleBooking = booking },
                        onShowQr = { qrBooking = booking }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        qrBooking?.let { booking ->
            QrDialog(
                booking = booking,
                onDismiss = { qrBooking = null }
            )
        }

        rescheduleBooking?.let { booking ->
//        RescheduleDialog(
//            booking = booking,
//            onDismiss = { rescheduleBooking = null },
//            onConfirm = { newDate ->
//                rescheduleBooking = null
//            }
//        )
        }
    }
}

@Preview
@Composable
private fun ProfileScreenDarkPreview() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
       // ProfileScreenContent()
    }
}

@Preview
@Composable
private fun ProfileScreenLightPreview() {
    MaterialTheme(
        colorScheme = lightColorScheme()
    ) {
        // ProfileScreenContent()
    }
}

