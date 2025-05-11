package com.example.bookmydoctor.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookmydoctor.model.Appointment
import com.example.bookmydoctor.viewmodel.AppointmentViewModel
import kotlinx.coroutines.launch

@Composable
fun AppointmentHistoryScreen(
    navController: NavController,
    viewModel: AppointmentViewModel
) {
    val scope = rememberCoroutineScope()
    val appointments = viewModel.appointmentList

    // Trigger fetch on first load
    LaunchedEffect(Unit) {
        viewModel.loadAppointments()
    }

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
        if (appointments.isEmpty()) {
            Text(
                text = "No appointments booked.",
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(appointments) { appointment ->
                    AppointmentCard(
                        appointment = appointment,
                        onCancel = {
                            scope.launch {
                                viewModel.deleteAppointment(appointment)
                                viewModel.loadAppointments()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AppointmentCard(
    appointment: Appointment,
    onCancel: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${appointment.name}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Age: ${appointment.age}")
            Text(text = "Gender: ${appointment.gender}")
            Text(text = "Department: ${appointment.department}")
            Text(text = "Symptoms: ${appointment.symptoms}")
            Text(text = "Date: ${appointment.date}")
            Text(text = "Time: ${appointment.time}")

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onCancel,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
            ) {
                Text("Cancel Appointment", color = Color.White)
            }
        }
    }
}

