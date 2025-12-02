package Presentation.SettingsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.screenone.ui.theme.*

@Composable
fun SettingsScreen(
    navController: NavController? = null,
    viewModel: SettingsViewModel = viewModel()
) {

    val soundEnabled by viewModel.soundEnabled.collectAsState()
    val event by viewModel.uiEvent.collectAsState()

    // Navigation Events
    LaunchedEffect(event) {
        when (event) {
            is SettingsUiEvent.NavigateHome -> navController?.navigate("activity")
            is SettingsUiEvent.NavigateStats -> navController?.navigate("activity")
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamBackground)
            .padding(horizontal = 20.dp)
    ) {

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Settings",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            colors = CardDefaults.cardColors(RowBackground)
        ) {

            Column {

                SettingRow(Icons.Default.Settings, "General", showArrow = true)
                Divider(color = DividerColor)

                SettingRow(Icons.Default.Flight, "Vacation Mode", showArrow = true)
                Divider(color = DividerColor)

                SettingRow(Icons.Default.Lock, "Security", showArrow = true)
                Divider(color = DividerColor)

                SettingRow(Icons.Default.Notifications, "Notifications", showArrow = true)
                Divider(color = DividerColor)

                SettingRow(
                    Icons.Default.VolumeUp,
                    "Sounds",
                    switchState = soundEnabled,
                    onSwitchChange = { viewModel.toggleSound(it) },
                    switchColor = ActiveGreen
                )
                Divider(color = DividerColor)

                SettingRow(Icons.Default.Notifications, "Notifications", showArrow = true)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        SettingsBottomNavBar(
            onHomeClick = { viewModel.onHomeClick() },
            onStatsClick = { viewModel.onStatsClick() },
            onSettingsClick = { viewModel.onSettingsClick() }
        )
    }
}

// ===============================
// Setting Row
// ===============================
@Composable
fun SettingRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    showArrow: Boolean = false,
    switchState: Boolean? = null,
    onSwitchChange: ((Boolean) -> Unit)? = null,
    switchColor: Color = ActiveGreen
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.DarkGray,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextColor
        )

        Spacer(modifier = Modifier.weight(1f))

        if (switchState != null && onSwitchChange != null) {
            Switch(
                checked = switchState,
                onCheckedChange = onSwitchChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = switchColor
                )
            )
        }

        if (showArrow) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.DarkGray,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

// ===============================
// BottomNavBar
// ===============================
@Composable
fun SettingsBottomNavBar(
    onHomeClick: () -> Unit,
    onStatsClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Box(
        modifier = Modifier
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

            }

            Icon(
                Icons.Default.ShowChart,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { onStatsClick() }
            )

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(ActiveGreen)
                    .clickable { onSettingsClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen()
}
