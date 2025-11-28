package com.example.habittracker.di

import com.example.habittracker.data.repository.HabitRepositoryImpl
import com.example.habittracker.domain.repository.HabitRepository

object AppModule {

    val habitRepository: HabitRepository by lazy {
        HabitRepositoryImpl()
    }
}