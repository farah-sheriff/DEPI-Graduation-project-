package com.example.habittracker.domain.repository

import com.example.habittracker.domain.model.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    fun getHabits(): Flow<List<Habit>>
    suspend fun getHabitById(id: Long): Habit?
    suspend fun addHabit(habit: Habit): Long
    suspend fun updateHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)
}