package com.example.bookmydoctor.model

data class Doctor(
    val name: String,
    val specialty: String,
    val experience: String,
    val certifications: List<String>,
    val languages: List<String>,
    val imageResId: Int // 👈 Add this
)
