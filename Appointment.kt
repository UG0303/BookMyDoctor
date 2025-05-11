package com.example.bookmydoctor.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments")
data class Appointment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val age: String,
    val gender: String,
    val department: String,
    val symptoms: String,
    val date: String,
    val time: String
)
