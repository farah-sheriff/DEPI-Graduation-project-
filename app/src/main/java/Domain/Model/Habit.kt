package com.example.habittracker.domain.model

import androidx.compose.ui.graphics.Color

data class Habit(
    val id: Long = System.currentTimeMillis(),
    val title: String,
    val tag: String = "New",
    val tagColor: Color = Color(0xFFFF4081),
    val time: String = "",
    val iconRes: Int,
    val isChecked: Boolean = false
)