package com.example.habittracker.domain.model


import androidx.compose.ui.graphics.Color

data class Habit(
    val id: Long = System.currentTimeMillis(),
    val title: String,               // اسم العادة
    val note: String = "",           // ملاحظة (اختياري)
    val duration: Int = 30,          // مدة العادة بالدقائق
    val notificationsEnabled: Boolean = true,  // هل الإشعارات مفعلة
    val tag: String = "New",
    val tagColor: Color = Color(0xFFFF4081),
    val time: String = "",           // وقت العادة
    val isChecked: Boolean = false   // هل العادة مكتملة
)
