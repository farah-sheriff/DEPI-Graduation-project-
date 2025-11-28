package com.example.habittracker.domain.repository

import com.example.habittracker.domain.model.Habit

interface HabitRepository {
    fun getHabits(): List<Habit>
    fun addHabit(habit: Habit)
}