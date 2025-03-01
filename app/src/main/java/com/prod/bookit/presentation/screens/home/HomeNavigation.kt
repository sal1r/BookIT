package com.prod.bookit.presentation.screens.home

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prod.bookit.presentation.navigation.BottomNavBar
import com.prod.bookit.presentation.navigation.BottomNavItem
import com.prod.bookit.presentation.screens.home.main.MainScreen
import com.prod.bookit.R

@Composable
fun HomeNavigation(rootNavController: NavHostController) {
    val homeNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = homeNavController,
                destinations = listOf(
                    BottomNavItem(
                        route = HomeNavDestinations.Main,
                        labelRes = R.string.main,
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home
                    )
                )
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = homeNavController,
            modifier = Modifier.padding(innerPadding),
            startDestination = HomeNavDestinations.Main,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) {
            composable<HomeNavDestinations.Main> {
                MainScreen()
            }
        }
    }
}