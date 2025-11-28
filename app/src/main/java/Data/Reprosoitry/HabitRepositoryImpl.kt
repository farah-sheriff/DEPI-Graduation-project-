package com.example.habittracker.data.repository

import com.example.habittracker.domain.model.Habit
import com.example.habittracker.domain.repository.HabitRepository

class HabitRepositoryImpl : HabitRepository {

    private val habits = mutableListOf<Habit>()

    override fun getHabits(): List<Habit> = habits

    override fun addHabit(habit: Habit) {
        habits.add(habit)
    }
}