package com.example.habittracker2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.habittracker2.navigation.AppNavHost
import com.example.habittracker2.theme.HabitTracker2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HabitTracker2Theme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}
