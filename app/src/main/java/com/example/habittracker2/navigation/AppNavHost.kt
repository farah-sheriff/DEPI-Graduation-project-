package com.example.habittracker2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.habittracker2.ui.screens.ActivityScreen
import com.example.habittracker2.ui.screens.SettingsScreen

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "activity"
    ) {

        composable("activity") {
            ActivityScreen(navController)
        }

        composable("settings") {
            SettingsScreen(navController)
        }
    }
}


