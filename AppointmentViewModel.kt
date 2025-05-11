package com.example.bookmydoctor.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookmydoctor.model.Appointment
import com.example.bookmydoctor.repository.AppointmentRepository
import kotlinx.coroutines.launch

class AppointmentViewModel(
    private val appointmentRepository: AppointmentRepository
) : ViewModel() {

    val name = mutableStateOf("")
    val age = mutableStateOf("")
    val gender = mutableStateOf("Male")
    val department = mutableStateOf("Cardiology")
    val symptoms = mutableStateOf("")
    val date = mutableStateOf("")
    val time = mutableStateOf("")

    // Live list of appointments for display
    val appointmentList = SnapshotStateList<Appointment>()

    fun saveAppointment(appointment: Appointment) {
        viewModelScope.launch {
            appointmentRepository.insert(appointment)
        }
    }

    fun loadAppointments() {
        viewModelScope.launch {
            appointmentRepository.getAllAppointments().collect { appointments ->
                appointmentList.clear()
                appointmentList.addAll(appointments)
            }
        }
    }


    fun deleteAppointment(appointment: Appointment) {
        viewModelScope.launch {
            appointmentRepository.delete(appointment)
            appointmentList.remove(appointment) // Optimistic update
        }
    }
}
