package Presentation.gender

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.screenone.ui.theme.LightBeige
import com.example.screenone.ui.theme.MoeGreen
import com.example.screenone.ui.theme.TextBlack
import com.example.screenone.ui.theme.TextWhite

@Composable
fun GenderSelectionScreen(navController: NavController, name: String) {

    var selectedGender by remember { mutableStateOf<String?>(null) }

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
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Create Account",
                fontSize = 28.sp,
                color = TextBlack,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Choose your gender",
                fontSize = 18.sp,
                color = TextBlack,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(48.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
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


        Button(
            onClick = {
                if (selectedGender != null)
                    navController.navigate("home") {
                        popUpTo("gender_selection/$name") { inclusive = true }
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
        Text(emoji, fontSize = 48.sp)
        Spacer(Modifier.height(8.dp))
        Text(label, fontSize = 14.sp)
    }
}
