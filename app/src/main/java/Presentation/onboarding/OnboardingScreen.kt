package com.example.habittracker.presentation.onboarding

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
import com.example.screenone.ui.theme.TextWhite
import com.example.screenone.ui.theme.DarkBackground
import com.example.screenone.ui.theme.MoeGreen




@Composable
fun OnboardingScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
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
                Text("MOE", fontSize = 48.sp, color = TextWhite, fontWeight = FontWeight.Black)

                Image(
                    painter = painterResource(id = com.example.screenone.R.drawable.pulseimage),
                    contentDescription = null,
                    modifier = Modifier.width(120.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate("welcome") },
                modifier = Modifier.fillMaxWidth() .height(56.dp),
                colors = ButtonDefaults.buttonColors(MoeGreen),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text("Get started", color = TextWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.height(32.dp))
        }

        Image(
            painter = painterResource(id = com.example.screenone.R.drawable.moeimage),
            contentDescription = "Moe Character",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(350.dp)
                .offset(x = 50.dp, y = 50.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    OnboardingScreen(navController = rememberNavController())
}
