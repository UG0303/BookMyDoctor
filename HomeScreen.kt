package com.example.bookmydoctor.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFB3E5FC), Color(0xFF0288D1)) // Light blue to deep teal
                )
            )
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val buttonModifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .height(56.dp)

            HomeButton("👩‍⚕️ View Doctors", buttonModifier) {
                navController.navigate("doctor_list")
            }

            HomeButton("📅 Book Appointment", buttonModifier) {
                navController.navigate("booking_form")
            }

            HomeButton("🏥 Clinic Information", buttonModifier) {
                navController.navigate("clinic_info")
            }

            HomeButton("📝 Appointment History", buttonModifier) {
                navController.navigate("history")
            }
        }
    }
}

@Composable
fun HomeButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
    ) {
        Text(text, color = Color.White)
    }
}
