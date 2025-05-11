package com.example.bookmydoctor.ui.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookmydoctor.viewmodel.AppointmentViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentBookingScreen(
    navController: NavController,
    viewModel: AppointmentViewModel
) {
    val context = LocalContext.current

    val name = viewModel.name
    val age = viewModel.age
    val gender = viewModel.gender
    val department = viewModel.department
    val symptoms = viewModel.symptoms
    val date = viewModel.date
    val time = viewModel.time

    var agreed by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val departments = listOf("Cardiology", "Dermatology", "Pediatrics", "Neurology")

    // Validation States
    var showError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE0F7FA), Color(0xFF80DEEA))
                )
            )
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Full Name") },
                isError = showError && name.value.isEmpty(),
                modifier = Modifier.fillMaxWidth()
            )
            if (showError && name.value.isEmpty()) {
                Text("Please enter your name", color = Color.Red, style = MaterialTheme.typography.labelSmall)
            }

            OutlinedTextField(
                value = age.value,
                onValueChange = { age.value = it },
                label = { Text("Age") },
                isError = showError && age.value.isEmpty(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            if (showError && age.value.isEmpty()) {
                Text("Please enter your age", color = Color.Red, style = MaterialTheme.typography.labelSmall)
            }

            // Gender Selection
            Text("Gender:")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                RadioButton(selected = gender.value == "Male", onClick = { gender.value = "Male" })
                Text("Male")
                RadioButton(selected = gender.value == "Female", onClick = { gender.value = "Female" })
                Text("Female")
            }

            // Department Dropdown
            Box {
                OutlinedTextField(
                    value = department.value,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Department") },
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    departments.forEach { dept ->
                        DropdownMenuItem(
                            text = { Text(dept) },
                            onClick = {
                                department.value = dept
                                expanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = symptoms.value,
                onValueChange = { symptoms.value = it },
                label = { Text("Symptoms") },
                modifier = Modifier.fillMaxWidth()
            )

            // Date Picker
            Button(
                onClick = {
                    val calendar = Calendar.getInstance()
                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            date.value = "$dayOfMonth/${month + 1}/$year"
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(if (date.value.isNotEmpty()) "Date: ${date.value}" else "Select Date")
            }
            if (showError && date.value.isEmpty()) {
                Text("Please select a date", color = Color.Red, style = MaterialTheme.typography.labelSmall)
            }

            // Time Picker
            Button(
                onClick = {
                    val calendar = Calendar.getInstance()
                    TimePickerDialog(
                        context,
                        { _, hour, minute ->
                            time.value = "$hour:$minute"
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        false
                    ).show()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(if (time.value.isNotEmpty()) "Time: ${time.value}" else "Select Time")
            }
            if (showError && time.value.isEmpty()) {
                Text("Please select a time", color = Color.Red, style = MaterialTheme.typography.labelSmall)
            }

            // Agreement Checkbox
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = agreed, onCheckedChange = { agreed = it })
                Text("I agree to the terms and conditions.")
            }

            // Submit Button
            Button(
                onClick = {
                    if (name.value.isEmpty() || age.value.isEmpty() || date.value.isEmpty() || time.value.isEmpty() || !agreed) {
                        showError = true
                        Toast.makeText(
                            context,
                            "Please complete all fields and agree to the terms",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        showError = false
                        navController.navigate("confirmation")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Confirm Appointment", color = Color.White)
            }
        }
    }
}
