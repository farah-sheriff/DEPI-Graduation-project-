package com.example.habittracker.domain.model


import androidx.compose.ui.graphics.Color

data class Habit(
    val id: Long = System.currentTimeMillis(),
    val title: String,
    val note: String = "",
    val duration: Int = 30,
    val notificationsEnabled: Boolean = true,
    val tag: String = "New",
    val tagColor: Color = Color(0xFFFF4081),
    val time: String = "",
    val isChecked: Boolean = false
)
