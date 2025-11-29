package Presentation.home

import android.R.attr.name
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.screenone.ui.theme.LightBeige
import com.example.screenone.ui.theme.MoeGreen
import com.example.habittracker.domain.model.Habit

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNewHabitClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onGraphClick: () -> Unit
) {
    val state = viewModel.state.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBeige)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Welcome Text
            Text(
                text = "Welcome ",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // New Habit Button
            Button(
                onClick = onNewHabitClick,
                colors = ButtonDefaults.buttonColors(containerColor = MoeGreen),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .height(45.dp)
                    .width(180.dp)
            ) {
                Text(text = "New Habit +")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Empty State
            if (state.habits.isEmpty()) {
                Text(
                    text = "No habits yet. Add your first habit!",
                    fontSize = 18.sp
                )
            }

            // List of Habit Cards
            state.habits.forEach { habit ->
                HabitCard(habit = habit)
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(100.dp)) // Space for bottom bar
        }

        // Bottom Navigation Bar
        SettingsBottomNavBar(
            onHomeClick = { /* Optional: Scroll to top or refresh */ },
            onStatsClick = onGraphClick,
            onSettingsClick = onSettingsClick,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun HabitCard(habit: Habit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = habit.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            if (habit.note.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = habit.note,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Time: ${habit.time}", fontSize = 14.sp)
                Text(text = "Duration: ${habit.duration} min", fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun SettingsBottomNavBar(
    onHomeClick: () -> Unit,
    onStatsClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(70.dp)
                .clip(RoundedCornerShape(40.dp))
                .background(Color.Black),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onHomeClick() }
                )
                Text("Home", color = Color.White, fontSize = 12.sp)
            }

            Icon(
                Icons.Default.ShowChart,
                contentDescription = "Graph",
                tint = Color.Gray,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { onStatsClick() }
            )

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MoeGreen)
                    .clickable { onSettingsClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}
