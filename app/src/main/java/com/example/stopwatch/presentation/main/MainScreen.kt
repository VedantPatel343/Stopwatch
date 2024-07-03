package com.example.stopwatch.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.stopwatch.data.DataStore
import com.example.stopwatch.data.repo.DataStoreRepo
import com.example.stopwatch.presentation.Screens
import com.example.stopwatch.presentation.stopwatch.StopwatchScreen
import com.example.stopwatch.presentation.timer.TimerScreen
import com.example.stopwatch.util.getCurrentThemeColor
import com.example.stopwatch.util.getIconColorByCurrentTheme

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val dataStoreRepo = DataStoreRepo(DataStore(context))
    val currentTheme by dataStoreRepo.getTheme().collectAsState(initial = "")
    val themeColor = getCurrentThemeColor(currentTheme)
    val iconColor = getIconColorByCurrentTheme(currentTheme)
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation(navController = navController, themeColor = themeColor, iconColor = iconColor)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Stopwatch.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screens.Stopwatch.route) {
                StopwatchScreen()
            }
            composable(Screens.Timer.route) {
                TimerScreen()
            }
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController, themeColor: Color, iconColor: Color) {

    val screens = listOf(
        Screens.Stopwatch,
        Screens.Timer
    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    NavigationBar(
        containerColor = themeColor
    ) {
        screens.forEach { screen ->
            val selected = currentRoute == screen.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            popUpTo(0)
                        }
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = if (selected) screen.filledIcon else screen.outlinedIcon),
                        contentDescription = null,
                        tint = if (selected) iconColor else iconColor.copy(alpha = 0.3f)
                    )
                },
                label = {
                    Text(text = screen.title, color = if (selected) iconColor else iconColor.copy(alpha = 0.3f))
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = themeColor)
            )
        }

    }
}