package com.example.bookmydoctor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookmydoctor.viewmodel.AppointmentViewModel
import com.example.bookmydoctor.model.Appointment
import com.example.bookmydoctor.utils.generateAppointmentPdf

@Composable
fun ConfirmationScreen(
    navController: NavController,
    viewModel: AppointmentViewModel
) {
    val context = LocalContext.current

    // Save the appointment once when the screen loads
    LaunchedEffect(Unit) {
        val appointment = Appointment(
            name = viewModel.name.value,
            age = viewModel.age.value,
            gender = viewModel.gender.value,
            department = viewModel.department.value,
            symptoms = viewModel.symptoms.value,
            date = viewModel.date.value,
            time = viewModel.time.value
        )
        viewModel.saveAppointment(appointment)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("✅ Appointment Confirmed!", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))
        Text("Name: ${viewModel.name.value}")
        Text("Age: ${viewModel.age.value}")
        Text("Gender: ${viewModel.gender.value}")
        Text("Department: ${viewModel.department.value}")
        Text("Symptoms: ${viewModel.symptoms.value}")
        Text("Date: ${viewModel.date.value}")
        Text("Time: ${viewModel.time.value}")

        Spacer(modifier = Modifier.height(24.dp))

        // 📄 Download PDF Button
        Button(onClick = {
            val pdfContent = """
                Appointment Confirmation

                Name: ${viewModel.name.value}
                Age: ${viewModel.age.value}
                Gender: ${viewModel.gender.value}
                Department: ${viewModel.department.value}
                Symptoms: ${viewModel.symptoms.value}
                Date: ${viewModel.date.value}
                Time: ${viewModel.time.value}
            """.trimIndent()

            val fileName = "Appointment_${viewModel.name.value}_${viewModel.date.value.replace("/", "-")}"
            generateAppointmentPdf(context, fileName, pdfContent)

        }) {
            Text("Download PDF")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔁 Back to Home
        Button(onClick = {
            navController.navigate("home") {
                popUpTo("home") { inclusive = true }
            }
        }) {
            Text("Back to Home")
        }
    }
}
