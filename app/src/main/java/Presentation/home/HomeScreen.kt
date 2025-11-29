package Presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNewHabitClick: () -> Unit
) {
    val state = viewModel.state.collectAsState().value

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
