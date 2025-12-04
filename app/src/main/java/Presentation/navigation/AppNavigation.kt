package Presentation.navigation

import Domain.Model.Session
import Presentation.create_account.CreateAccountScreen
import Presentation.ActivityScreen.ActivityScreen
import Presentation.ActivityScreen.ActivityScreenViewModel
import Presentation.HabitDetails.HabitDetailsScreen
import Presentation.SettingsScreen.SettingsScreen
import Presentation.addhabit.NewHabitScreen
import Presentation.gender.GenderSelectionScreen
import Presentation.home.HomeScreen
import Presentation.home.HomeViewModel
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.habittracker.di.AppModule
import com.example.habittracker.presentation.onboarding.OnboardingScreen
import com.example.habittracker.presentation.welcome.WelcomeScreen


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val repository = remember { AppModule.provideHabitRepository(context) }
    val userRepository = remember { AppModule.provideUserRepository(context) }
    val sessionRepository = remember { AppModule.provideSessionRepository(context) }
    val homeViewModel = remember { HomeViewModel(repository, userRepository) }

    var startDestination by remember { mutableStateOf("onboarding") }

    LaunchedEffect(Unit) {
        val user = userRepository.getUserSync()
        startDestination = if (user != null) "home" else "onboarding"
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable("onboarding") {
            OnboardingScreen(navController)
        }

        composable("welcome") {
            WelcomeScreen(navController)
        }

        composable("create_account") {
            CreateAccountScreen(navController)
        }


        composable("new_habit") {
            NewHabitScreen(
                navController = navController,
                viewModel = homeViewModel
            )
        }

        composable(
            "gender_selection/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) {
            val name = it.arguments?.getString("name") ?: ""
            GenderSelectionScreen(navController, name)
        }


        composable("home") {
            HomeScreen(
                viewModel = homeViewModel,
                onNewHabitClick = { navController.navigate("new_habit") },
                onSettingsClick = { navController.navigate("settings") },
                onGraphClick = { navController.navigate("activity") },
                onHabitClick = { habitId ->
                    navController.navigate("habit_details/$habitId")
                },
                navController = navController
            )
        }

        composable(
            "habit_details/{habitId}",
            arguments = listOf(navArgument("habitId") { type = NavType.LongType })
        ) {
            val habitId = it.arguments?.getLong("habitId") ?: 0L
            var habit by remember { mutableStateOf<com.example.habittracker.domain.model.Habit?>(null) }
            var sessions by remember { mutableStateOf<List<Session>>(emptyList()) }
            val coroutineScope = rememberCoroutineScope()

            LaunchedEffect(habitId) {
                habit = homeViewModel.getHabitById(habitId)
                sessions = sessionRepository.getSessionsByHabitIdSync(habitId)
            }

            HabitDetailsScreen(
                navController = navController,
                habit = habit,
                sessions = sessions,
                onMarkComplete = { updatedHabit ->
                    coroutineScope.launch {
                        // Save session
                        sessionRepository.addSession(
                            Session(
                                habitId = habitId,
                                date = System.currentTimeMillis(),
                                duration = updatedHabit.duration
                            )
                        )
                        homeViewModel.updateHabit(updatedHabit.copy(isChecked = !updatedHabit.isChecked))
                        // Refresh habit and sessions
                        habit = homeViewModel.getHabitById(habitId)
                        sessions = sessionRepository.getSessionsByHabitIdSync(habitId)
                    }
                },
                onDelete = { habitToDelete ->
                    coroutineScope.launch {
                        sessionRepository.deleteSessionsByHabitId(habitId)
                        homeViewModel.deleteHabit(habitToDelete)
                    }
                }
            )
        }

        composable("activity") {
            ActivityScreen(
                navController = navController,
                viewModel = remember {
                    ActivityScreenViewModel(
                        repository,
                        AppModule.provideSessionRepository(context)
                    )
                }
            )
        }

        composable("settings") {
            SettingsScreen(navController)
        }
    }
}
