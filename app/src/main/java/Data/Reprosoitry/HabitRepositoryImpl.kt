package com.example.habittracker.data.repository



import Data.database.HabitDatabase
import Data.mapper.toDomain
import Data.mapper.toEntity
import android.content.Context
import com.example.habittracker.domain.model.Habit
import com.example.habittracker.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HabitRepositoryImpl(context: Context) : HabitRepository {

    private val database = HabitDatabase.getDatabase(context)
    private val habitDao = database.habitDao()

    override fun getHabits(): Flow<List<Habit>> {
        return habitDao.getAllHabits().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getHabitById(id: Long): Habit? {
        return habitDao.getHabitById(id)?.toDomain()
    }

    override suspend fun addHabit(habit: Habit): Long {
        return habitDao.insertHabit(habit.toEntity())
    }

    override suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(habit.toEntity())
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitDao.deleteHabit(habit.toEntity())
    }
}


