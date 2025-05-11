package com.example.bookmydoctor.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookmydoctor.model.Doctor
import com.example.bookmydoctor.R

@Composable
fun DoctorListScreen(navController: NavController) {
    val doctors = listOf(
        Doctor(
            name = "Dr. Priya Sharma",
            specialty = "Cardiologist",
            experience = "12 years",
            certifications = listOf("MBBS", "MD Cardiology"),
            languages = listOf("English", "Hindi"),
            imageResId = R.drawable.doctor1
        ),
        Doctor(
            name = "Dr. Aarav Mehta",
            specialty = "Dermatologist",
            experience = "8 years",
            certifications = listOf("MBBS", "MD Dermatology"),
            languages = listOf("English", "Tamil"),
            imageResId = R.drawable.doctor2
        ),
        Doctor(
            name = "Dr. Neha Kapoor",
            specialty = "Pediatrician",
            experience = "10 years",
            certifications = listOf("MBBS", "DCH"),
            languages = listOf("English", "Malayalam", "Hindi"),
            imageResId = R.drawable.doctor3
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFF0F4F8), Color(0xFFBBDEFB))
                )
            )
            .padding(16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(doctors) { doctor ->
                DoctorCard(doctor = doctor, onBookNow = {
                    navController.navigate("booking_form")
                })
            }
        }
    }
}

@Composable
fun DoctorCard(doctor: Doctor, onBookNow: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // 🖼 Profile Image
            Image(
                painter = painterResource(id = doctor.imageResId),
                contentDescription = doctor.name,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = doctor.name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF1565C0)
            )
            Text("Specialty: ${doctor.specialty}")
            Text("Experience: ${doctor.experience}")
            Text("Certifications: ${doctor.certifications.joinToString(", ")}")
            Text("Languages: ${doctor.languages.joinToString(", ")}")

            Spacer(modifier = Modifier.height(12.dp))

            // 📅 Book Now Button
            Button(
                onClick = onBookNow,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Book Now", color = Color.White)
            }
        }
    }
}
