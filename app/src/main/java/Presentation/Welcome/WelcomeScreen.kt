package com.example.habittracker.presentation.welcome


import android.R
import android.R.drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.TextStyle
import com.example.habittracker.presentation.onboarding.OnboardingScreen
import com.example.screenone.ui.theme.MoeGreen
import com.example.screenone.ui.theme.TextWhite


@Composable
fun WelcomeScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
        Image(
            painter = painterResource(id = com.example.screenone.R.drawable.moeimage),
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
                    .background(
                        Color(0xFF333333),
                        RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 0.dp
                        )
                    )
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
@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = rememberNavController())
}