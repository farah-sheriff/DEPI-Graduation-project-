package Presentation.addhabit

import Presentation.home.HomeViewModel
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.screenone.ui.theme.MoeGreen
import com.example.screenone.ui.theme.TextBlack
import java.util.*

@Composable
fun NewHabitScreen(navController: NavController, viewModel: HomeViewModel) {

    var habitName by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var timeText by remember { mutableStateOf("Pick Time") }
    var duration by remember { mutableStateOf(30f) }
    var notificationsEnabled by remember { mutableStateOf(true) }

    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val min = calendar.get(Calendar.MINUTE)

    val context = LocalContext.current
    val timePicker = TimePickerDialog(
        context,
        { _, h: Int, m: Int ->
            val amPm = if (h >= 12) "م" else "ص"
            val formattedHour = if (h % 12 == 0) 12 else h % 12
            timeText = "$formattedHour:${m.toString().padStart(2, '0')} $amPm"
        },
        hour,
        min,
        false
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        // Title
        Text(
            text = "New Habit",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = TextBlack
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Habit Name
        OutlinedTextField(
            value = habitName,
            onValueChange = { habitName = it },
            label = { Text("Habit Name") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MoeGreen,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = MoeGreen
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Note
        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Note (Optional)") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MoeGreen,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = MoeGreen
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Daily Schedule
        Text("Daily Schedule", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = timeText,
                fontSize = 18.sp,
                color = Color(0xFF6A1B9A)
            )

            Button(
                onClick = { timePicker.show() },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
            ) {
                Text("Pick Time", color = TextBlack)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Duration
        Text("Duration", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Slider(
            value = duration,
            onValueChange = { duration = it },
            valueRange = 10f..120f,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFFFFC107),
                activeTrackColor = TextBlack
            )
        )

        Text(
            text = "min ${duration.toInt()}",
            fontSize = 18.sp,
            color = Color(0xFF6A1B9A)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Enable Notifications
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enable Notifications", fontSize = 18.sp)
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Finish Button
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
        ) {
            Text("Finish", color = TextBlack, fontSize = 20.sp)
        }
    }
}
