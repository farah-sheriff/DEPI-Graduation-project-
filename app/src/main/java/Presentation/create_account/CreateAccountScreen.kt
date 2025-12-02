package Presentation.create_account

import android.R
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.example.habittracker.di.AppModule
import com.example.habittracker.presentation.create_account.CreateAccountViewModel
import com.example.habittracker.presentation.onboarding.OnboardingScreen
import com.example.screenone.ui.theme.LightBeige
import com.example.screenone.ui.theme.MoeGreen
import com.example.screenone.ui.theme.TextBlack
import kotlinx.coroutines.launch


@Composable
fun CreateAccountScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val viewModel = remember {
        CreateAccountViewModel(
            AppModule.provideUserRepository(context)
        )
    }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBeige)
            .padding(32.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Text("NAME")
            CustomTextField(
                value = viewModel.name.value,
                onValueChange = { viewModel.name.value = it },
                placeholder = "Enter name"
            )

            Spacer(Modifier.height(16.dp))

            Text("SURNAME")
            CustomTextField(
                value = viewModel.surname.value,
                onValueChange = { viewModel.surname.value = it },
                placeholder = "Enter surname"
            )

            Spacer(Modifier.height(16.dp))

            Text("BIRTHDATE")
            CustomTextField(
                value = viewModel.birthdate.value,
                onValueChange = { viewModel.birthdate.value = it },
                placeholder = "mm/dd/yyyy"
            )
        }

        Button(
            onClick = {
                if (viewModel.isFormValid()) {
                    coroutineScope.launch {
                        viewModel.saveUser()
                        navController.navigate("gender_selection/${viewModel.name.value}")
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = MoeGreen),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Text("Next")
        }
    }
}
@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, placeholder: String) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = Color.LightGray
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = MoeGreen,
            unfocusedIndicatorColor = Color.LightGray,
            focusedTextColor = TextBlack,
            unfocusedTextColor = TextBlack
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun CreateAccountScreenPreview() {
    CreateAccountScreen(navController = rememberNavController())
}