package com.example.bookmydoctor.data

import androidx.room.*
import com.example.bookmydoctor.model.Appointment
import kotlinx.coroutines.flow.Flow

@Dao
interface AppointmentDao {
    @Insert
    suspend fun insert(appointment: Appointment)

    @Delete
    suspend fun delete(appointment: Appointment)

    @Query("SELECT * FROM appointments")
    fun getAllAppointments(): Flow<List<Appointment>>

}

