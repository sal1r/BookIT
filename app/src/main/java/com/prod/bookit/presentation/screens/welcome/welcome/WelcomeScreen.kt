package com.prod.bookit.presentation.screens.welcome.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.prod.bookit.R
import com.prod.bookit.domain.repository.AuthRepository
import com.prod.bookit.presentation.screens.RootNavDestinations
import com.prod.bookit.presentation.screens.welcome.login.LoginScreen
import com.prod.bookit.presentation.screens.welcome.register.RegisterScreen
import com.prod.bookit.presentation.theme.DarkBlueTheme
import com.prod.bookit.presentation.theme.LightBlueTheme
import kotlinx.coroutines.launch
import org.koin.compose.getKoin

@Composable
fun WelcomeScreen(
    rootNavController: NavHostController,
    authRepository: AuthRepository = getKoin().get()
) {
    if (authRepository.getToken() != null) {
        rootNavController.navigate(RootNavDestinations.Home) {
            popUpTo(RootNavDestinations.Welcome) { inclusive = true }
        }
    }

   WelcomeScreenContent(rootNavController)
}

@Composable
private fun WelcomeScreenContent(rootNavController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf(R.string.sign_in, R.string.sign_up)
    val pagerState = rememberPagerState { tabs.size }

    LaunchedEffect(pagerState.currentPage) {
        selectedTab = pagerState.currentPage
    }

    Scaffold(
        topBar = {
            // пока так
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        ) {
            Column {
                TabRow(
                    selectedTabIndex = selectedTab,
                    indicator = { tabPositions ->
                        if (pagerState.currentPage < tabPositions.size) {
                            Box(
                                modifier = Modifier
                                    .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                                    .clip(MaterialTheme.shapes.medium)
                                    .background(MaterialTheme.colorScheme.primaryContainer)
                                    .fillMaxSize()
                            )
                        }
                    },
                    divider = {}
                ) {
                    tabs.forEachIndexed { index, title ->
                        val selected = selectedTab == index
                        Tab(
                            selectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            unselectedContentColor = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .zIndex(1f)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .clip(MaterialTheme.shapes.medium),
                            selected = selected,
                            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                            text = { Text(text = stringResource(title)) },

                        )
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) { page ->
                    when (page) {
                        0 -> LoginScreen(rootNavController)
                        1 -> RegisterScreen(rootNavController)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun WelcomeScreenDarkPreview() {
    MaterialTheme(
        colorScheme = DarkBlueTheme
    ) {
        WelcomeScreenContent(rememberNavController())
    }
}

@Preview
@Composable
private fun WelcomeScreenLightPreview() {
    MaterialTheme(
        colorScheme = LightBlueTheme
    ) {
        //WelcomeScreenContent()
    }
}