package com.example.habittracker.di

import Data.Reprosoitry.SessionRepositoryImpl
import Data.Reprosoitry.UserRepositoryImpl
import Domain.Repository.SessionRepository
import Domain.Repository.UserRepository
import com.example.habittracker.data.repository.HabitRepositoryImpl



import android.content.Context
import com.example.habittracker.domain.repository.HabitRepository


object AppModule {

    fun provideHabitRepository(context: Context): HabitRepository {
        return HabitRepositoryImpl(context)
    }

    fun provideUserRepository(context: Context): UserRepository {
        return UserRepositoryImpl(context)
    }

    fun provideSessionRepository(context: Context): SessionRepository {
        return SessionRepositoryImpl(context)
    }
}