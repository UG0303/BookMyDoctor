package com.example.bookmydoctor.repository

import com.example.bookmydoctor.data.AppointmentDao
import com.example.bookmydoctor.model.Appointment
import kotlinx.coroutines.flow.Flow

class AppointmentRepository(private val dao: AppointmentDao) {

    suspend fun insert(appointment: Appointment) {
        dao.insert(appointment)
    }

    suspend fun delete(appointment: Appointment) {
        dao.delete(appointment) // ✅ FIXED
    }

    fun getAllAppointments(): Flow<List<Appointment>> {
        return dao.getAllAppointments()
    }
}
