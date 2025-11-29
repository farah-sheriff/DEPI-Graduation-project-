package Presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.habittracker.presentation.onboarding.OnboardingScreen
import com.example.screenone.ui.theme.LightBeige
import com.example.screenone.ui.theme.MoeGreen
import com.example.screenone.ui.theme.TextWhite

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNewHabitClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBeige)
            .padding(24.dp)
    ) {
        val state = viewModel.state.collectAsState().value
        Column(
            modifier = Modifier
                .fillMaxSize() // 1. Fills the entire screen height
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally, // 2. Centers items horizontally
            verticalArrangement = Arrangement.Center // 3. Centers items vertically (Middle of screen)
        ) {

            Row(

                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = com.example.screenone.R.drawable.cup),
                    contentDescription = "cup_image",
                    modifier = Modifier
                        .size(150.dp)
                )


                Spacer(modifier = Modifier.width(16.dp))


                Image(
                    painter = painterResource(id = com.example.screenone.R.drawable.polygon1),
                    contentDescription = "polygon_image",
                    modifier = Modifier
                        .size(150.dp)
                )
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Welcome back!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = onNewHabitClick,
                colors = ButtonDefaults.buttonColors(containerColor = MoeGreen),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .height(45.dp)
                    .width(180.dp)
            ) {
                Text(text = "New Habit   +")
            }

            Spacer(modifier = Modifier.height(120.dp))

            if (state.habits.isEmpty()) {
                Text(
                    text = "No habits yet. Add your first habit!",
                    fontSize = 18.sp
                )
            }
        }
    }
}