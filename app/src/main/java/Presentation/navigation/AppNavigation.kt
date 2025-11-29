package Presentation.navigation
import CreateAccountScreen
import Presentation.ActivityScreen.ActivityScreen
import Presentation.SettingsScreen.SettingsScreen
import Presentation.addhabit.NewHabitScreen
import Presentation.gender.GenderSelectionScreen
import Presentation.home.HomeScreen
import Presentation.home.HomeViewModel
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
//import java.time.format.TextStyle
import com.example.habittracker.presentation.onboarding.OnboardingScreen
import com.example.habittracker.presentation.welcome.WelcomeScreen

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()


    val homeViewModel = HomeViewModel()

    NavHost(
        navController = navController,
        startDestination = "onboarding"
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
                onNewHabitClick = { navController.navigate("new_habit") }
            )
        }
        composable("activity") {
            ActivityScreen(navController)
        }

        composable("settings") {
            SettingsScreen(navController)
        }
    }
}
