package com.prod.bookit.presentation.screens

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prod.bookit.domain.repository.AuthRepository
import com.prod.bookit.presentation.screens.settings.SettingsScreen
import com.prod.bookit.presentation.screens.welcome.welcome.WelcomeScreen
import com.prod.bookit.presentation.screens.booking.BookingScreen
import com.prod.bookit.presentation.screens.coworkings.CoworkingsScreen
import com.prod.bookit.presentation.screens.profile.ProfileScreen
import org.koin.compose.getKoin

@Composable
fun RootNavigation() {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = getKoin().get<AuthRepository>().getToken()?.let { RootNavDestinations.Booking } ?: RootNavDestinations.Welcome,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {

        composable<RootNavDestinations.Welcome> {
            WelcomeScreen(
                rootNavController = rootNavController
            )
        }

        composable<RootNavDestinations.Settings> {
            SettingsScreen(
                rootNavController = rootNavController
            )
        }

        composable<RootNavDestinations.Booking> {
            BookingScreen(
                rootNavController = rootNavController
            )
        }

        composable<RootNavDestinations.Coworkings> {
            CoworkingsScreen(
                rootNavController = rootNavController
            )
        }

        composable<RootNavDestinations.Profile> {
            ProfileScreen(
                rootNavController = rootNavController
            )
        }
    }
}