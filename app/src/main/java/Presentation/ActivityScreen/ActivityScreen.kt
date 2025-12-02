package Presentation.ActivityScreen


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ActivityScreen(
    navController: NavController,
    viewModel: ActivityScreenViewModel
) {

    var selectedTab by remember { mutableStateOf(0) }
    val state = viewModel.state.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6FBEF))
            .padding(horizontal = 22.dp)
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Statistics",
            fontSize = 14.sp,
            color = Color.Gray.copy(alpha = 0.4f)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Activity",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        val tabs = listOf("Daily", "Weekly", "Monthly")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            tabs.forEachIndexed { index, title ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp)
                        .background(
                            if (selectedTab == index) Color(0xFF4CAF50)
                            else Color(0xFFDFF2DF),
                            RoundedCornerShape(10.dp)
                        )
                        .clickable { selectedTab = index },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        color = if (selectedTab == index) Color.White else Color(0xFF2A4F2A),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.DarkGray,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { }
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "May 28 – Jun 3",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.width(10.dp))

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = Color.DarkGray,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { }
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {

            Column(modifier = Modifier.padding(20.dp)) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Icon(
                        imageVector = Icons.Filled.ShowChart,
                        contentDescription = null,
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Habits",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Surface(
                        color = Color(0xFFFFE3C2),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "🔥 ${state.completedHabits}/${state.totalHabits} habits",
                            color = Color(0xFFCC5500),
                            fontSize = 13.sp,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                ) {
                    val chartData = when (selectedTab) {
                        0 -> state.dailyStats.ifEmpty { listOf(0f, 0f, 0f, 0f, 0f, 0f, 0f) }
                        1 -> state.weeklyStats.ifEmpty { listOf(0f, 0f, 0f, 0f) }
                        else -> state.monthlyStats.ifEmpty { listOf(0f, 0f, 0f, 0f, 0f, 0f) }
                    }
                    SmoothLineChart(data = chartData)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        AppBottomNavBar(
            onHomeClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
            onActivityClick = { /* Already on activity */ },
            onSettingsClick = { navController.navigate("settings") }
        )
    }
}

@Composable
fun SmoothLineChart(data: List<Float>) {

    Canvas(modifier = Modifier.fillMaxSize()) {
        if (data.isEmpty() || data.all { it == 0f }) {
            // Show empty state or default line
            return@Canvas
        }

        val xStep = if (data.size > 1) size.width / (data.size - 1) else size.width
        val maxY = data.maxOrNull() ?: 1f
        val minY = 0f
        val yRange = if (maxY > minY) maxY - minY else 1f

        val path = Path()

        data.forEachIndexed { i, v ->
            val x = i * xStep
            val normalizedValue = if (yRange > 0) (v - minY) / yRange else 0f
            val y = size.height - (normalizedValue * size.height)

            if (i == 0) path.moveTo(x, y)
            else path.lineTo(x, y)
        }

        drawPath(
            path,
            color = Color(0xFF4CAF50),
            style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
        )

        // Draw points
        data.forEachIndexed { i, v ->
            val x = i * xStep
            val normalizedValue = if (yRange > 0) (v - minY) / yRange else 0f
            val y = size.height - (normalizedValue * size.height)

            drawCircle(
                color = Color(0xFF4CAF50),
                radius = 6.dp.toPx(),
                center = Offset(x, y)
            )
        }
    }
}

@Composable
fun AppBottomNavBar(
    onHomeClick: () -> Unit,
    onActivityClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Surface(
        tonalElevation = 15.dp,
        shadowElevation = 20.dp,
        shape = RoundedCornerShape(25.dp),
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(vertical = 8.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { onHomeClick() }
            )

            Icon(
                imageVector = Icons.Filled.ShowChart,
                contentDescription = null,
                tint = Color(0xFF4CAF50),
                modifier = Modifier
                    .size(36.dp)
                    .clickable { onActivityClick() }
            )

            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { onSettingsClick() }
            )
        }
    }
}
