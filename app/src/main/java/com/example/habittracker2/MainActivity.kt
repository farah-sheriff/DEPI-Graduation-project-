package com.example.habittracker2

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
//import java.time.format.TextStyle
import androidx.compose.ui.text.TextStyle

// --- Shared Colors ---
val DarkBackground = Color(0xFF2D2D2D)
val LightBeige = Color(0xFFF4F7E6) // Light background for new screens
val MoeGreen = Color(0xFF4CAF50)
val TextWhite = Color.White
val TextBlack = Color.Black
val BannerYellow = Color(0xFFE6EEB8)

data class Habit(
    val id: Long = System.currentTimeMillis(), // Unique ID
    val title: String,
    val tag: String = "New",
    val tagColor: Color = Color(0xFFFF4081),
    val time: String = "",
    val iconRes: Int = R.drawable.ic_menu_agenda,
    var isChecked: Boolean = false
)



// --- Main Activity Setup ---
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation()
                }
            }
        }
    }
}

// --- Navigation Logic ---
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") {
            OnboardingScreen(navController)
        }
        // 2. Second Screen (Welcome with Bubble)
        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable("create_account") {
            CreateAccountScreen(navController)
        }
        // 3. Third Screen (Form)
        composable(
            route = "profile/{name}/{surname}/{birthdate}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("surname") { type = NavType.StringType },
                navArgument("birthdate") { type = NavType.StringType }
            )
        )

        { backStackEntry ->
            // Extract the arguments passed from the previous screen
            val name = backStackEntry.arguments?.getString("name") ?: "Unknown"
            val surname = backStackEntry.arguments?.getString("surname") ?: "Unknown"
            val birthdate = backStackEntry.arguments?.getString("birthdate") ?: "Unknown"

            ProfileDetailsScreen(navController, name, surname, birthdate)
        }
        composable("gender_selection") {
            GenderSelectionScreen(navController, name="" )
        }
        composable(
            route = "gender_selection/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "User"
            GenderSelectionScreen(navController, name)
        }

        }
    }


// --- The Figma Screen Implementation ---
@Composable
fun OnboardingScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // 1. Main Content Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 32.dp, end = 32.dp, top = 80.dp, bottom = 32.dp)
        ) {
            // --- Text Section ---

            // "GO FOR"
            Box {
                Text(
                    text = "GO FOR",
                    color = TextWhite,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    lineHeight = 48.sp
                )
                // Placeholder for the 'Sunburst' icon above "FOR"
                // Add 'sunburst_icon.png' to drawables to use this:


            }

            // "BETTER HABITS" (Green)
            Text(
                text = "BETTER",
                color = MoeGreen,
                fontSize = 48.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 48.sp
            )
            Text(
                text = "HABITS",
                color = MoeGreen,
                fontSize = 48.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 48.sp
            )

            // "WITH MOE" (White)
            Text(
                text = "WITH",
                color = TextWhite,
                fontSize = 48.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 48.sp
            )

            Column {
                Text(
                    text = "MOE",
                    color = TextWhite,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    lineHeight = 48.sp
                )
                Image(
                    painter = painterResource(id = com.example.screenone.R.drawable.pulse_1),
                    contentDescription = "pulse image",
                    modifier = Modifier
                        .width(120.dp) // Adjust width as needed
                        .offset(y = (-1).dp)
                )
                // Squiggle Line under MOE
                // Ideally, use an image here.
                // Example: Image(painter = painterResource(id = R.drawable.squiggle), ...)
                // For now, I'll simulate it with a simple Text or placeholder box

            }

            Spacer(modifier = Modifier.weight(1f))

            // --- Button Section ---
            Button(
                onClick = { navController.navigate("welcome") }, // Connects to next screen
                colors = ButtonDefaults.buttonColors(containerColor = MoeGreen),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Get started",
                    color = TextWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        Image(
            painter = painterResource(id = com.example.screenone.R.drawable.moe_char_png),
            contentDescription = "Moe Character",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd)
                .offset(x = 50.dp, y = 50.dp) // Tweaked offset to push it slightly off-screen like Figma
                .size(350.dp) // Adjusted size
        )

    }
}

// --- The Next Screen (Target) ---
@Composable
fun WelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background( Color.White)

    ) {
        Image(
            painter = painterResource(id = com.example.screenone.R.drawable.moe_char_png),
            contentDescription = "Moe Character",
            contentScale = ContentScale.Crop, // Crop to make it look like it's peeking
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 40.dp, y = 150.dp) // Adjust position to match design
                .size(300.dp)
                .rotate(-10f) // Slight tilt if needed
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 260.dp, start = 40.dp)
        ) {
            // Bubble Background
            Box(
                modifier = Modifier
                    .background(Color(0xFF333333), RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 0.dp))
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "IT'S MORE FUN\nTOGETHER",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(letterSpacing = 2.sp) // Handwritten style spacing
                )
            }
    }
    Column(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(32.dp)
            .fillMaxWidth()
    ) {
        Button(
            onClick = { navController.navigate("create_account") },
            colors = ButtonDefaults.buttonColors(containerColor = MoeGreen),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Create Account", color = TextWhite, fontSize = 18.sp)
        }
    }
}
}
@Composable
fun CreateAccountScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var birthdate by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBeige)
            .padding(32.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Title
            Text(
                text = "Create Account",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextBlack
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Form Fields
            Text("NAME", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            CustomTextField(value = name, onValueChange = { name = it }, placeholder = "Enter name")
            Spacer(modifier = Modifier.height(24.dp))

            Text("SURNAME", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            CustomTextField(value = surname, onValueChange = { surname = it }, placeholder = "Enter your surname")
            Spacer(modifier = Modifier.height(24.dp))

            Text("BIRTHDATE", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            CustomTextField(value = birthdate, onValueChange = { birthdate = it }, placeholder = "mm/dd/yyyy")
        }

        // Bottom Button
        Button(
            // This button collects the data and goes to the Profile
            onClick = {
                if (name.isNotBlank() && surname.isNotBlank() && birthdate.isNotBlank()) {
                    navController.navigate("gender_selection")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = MoeGreen),
            shape = RoundedCornerShape(50), // Fully rounded like the design
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Next", color = TextWhite, fontSize = 18.sp)
        }
    }
}
@Composable
fun GenderSelectionScreen(navController: NavController, name: String) {
    var selectedGender by remember { mutableStateOf<String?>(null) } // "Male" or "Female"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBeige)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Create Account",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextBlack,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Choose your gender",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = TextBlack,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Gender Cards Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GenderCard(
                    emoji = "💁🏻‍♂️",
                    label = "Male",
                    isSelected = selectedGender == "Male",
                    onClick = { selectedGender = "Male" }
                )

                Spacer(modifier = Modifier.width(16.dp))

                GenderCard(
                    emoji = "🙋🏻‍♀️",
                    label = "Female",
                    isSelected = selectedGender == "Female",
                    onClick = { selectedGender = "Female" }
                )
            }
        }

        // Bottom Button
        Button(
            onClick = {
                if (selectedGender != null) {
                    navController.navigate("dashboard/$name")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedGender != null) MoeGreen else Color.Gray.copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(56.dp),
            enabled = selectedGender != null
        ) {
            Text("Next", color = TextWhite, fontSize = 18.sp)
        }
    }
}
@Composable
fun GenderCard(emoji: String, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) MoeGreen else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(text = emoji, fontSize = 48.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = label, fontWeight = FontWeight.Bold, color = TextBlack, fontSize = 14.sp)
    }
}
@Composable
fun ProfileDetailsScreen(navController: NavController, name: String, surname: String, birthdate: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBeige)
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome, $name!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MoeGreen
            )
            Spacer(modifier = Modifier.height(20.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Details", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextBlack)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Full Name: $name $surname", fontSize = 16.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Birthdate: $birthdate", fontSize = 16.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { navController.navigate("onboarding") },
                colors = ButtonDefaults.buttonColors(containerColor = DarkBackground)
            ) {
                Text("Start Over", color = TextWhite)
            }
        }
    }
}

@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, placeholder: String) {
    // We explicitly use Material3 TextField here to avoid conflicts
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = Color.LightGray
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = MoeGreen,
            unfocusedIndicatorColor = Color.LightGray,
            focusedTextColor = TextBlack,
            unfocusedTextColor = TextBlack
        ),
        modifier = Modifier.fillMaxWidth()
    )
}



@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    // Preview doesn't need real nav controller
    val navController = rememberNavController()
    OnboardingScreen(navController)
}