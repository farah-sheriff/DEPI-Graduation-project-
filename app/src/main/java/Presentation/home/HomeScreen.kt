package Presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import com.example.screenone.ui.theme.LightBeige
import com.example.screenone.ui.theme.MoeGreen
import com.example.habittracker.domain.model.Habit

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNewHabitClick: () -> Unit
) {
    val state = viewModel.state.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBeige)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()), // Enables scrolling if list is long
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Welcome Text
        Text(
            text = "Welcome back!",
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
