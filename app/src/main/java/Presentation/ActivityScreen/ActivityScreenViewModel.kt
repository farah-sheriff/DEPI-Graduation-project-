package Presentation.ActivityScreen


import Domain.Model.Session
import Domain.Repository.SessionRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.domain.model.Habit
import com.example.habittracker.domain.repository.HabitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.Calendar

class ActivityScreenViewModel(
    private val repository: HabitRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ActivityState())
    val state: StateFlow<ActivityState> = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            // Combine habits and sessions flows
            combine(
                repository.getHabits(),
                sessionRepository.getAllSessions()
            ) { habits, sessions ->
                val totalCount = habits.size
                val stats = calculateStats(sessions)

                ActivityState(
                    habits = habits,
                    completedHabits = sessions.size, // Total sessions completed
                    totalHabits = totalCount,
                    dailyStats = stats.daily,
                    weeklyStats = stats.weekly,
                    monthlyStats = stats.monthly
                )
            }.collect { activityState ->
                _state.value = activityState
            }
        }
    }

    private fun calculateStats(sessions: List<Session>): Stats {
        val calendar = Calendar.getInstance()
        val now = System.currentTimeMillis()

        // Calculate daily stats (last 7 days)
        val daily = (0..6).map { dayOffset ->
            calendar.timeInMillis = now
            calendar.add(Calendar.DAY_OF_YEAR, -dayOffset)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            val dayStart = calendar.timeInMillis
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            val dayEnd = calendar.timeInMillis

            sessions.count { session ->
                session.date >= dayStart && session.date < dayEnd
            }.toFloat()
        }.reversed() // Reverse to show oldest to newest

        // Calculate weekly stats (last 4 weeks)
        val weekly = (0..3).map { weekOffset ->
            calendar.timeInMillis = now
            calendar.add(Calendar.WEEK_OF_YEAR, -weekOffset)
            calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            val weekStart = calendar.timeInMillis
            calendar.add(Calendar.WEEK_OF_YEAR, 1)
            val weekEnd = calendar.timeInMillis

            sessions.count { session ->
                session.date >= weekStart && session.date < weekEnd
            }.toFloat()
        }.reversed()

        // Calculate monthly stats (last 6 months)
        val monthly = (0..5).map { monthOffset ->
            calendar.timeInMillis = now
            calendar.add(Calendar.MONTH, -monthOffset)
            calendar.set(Calendar.DAY_OF_MONTH, 1)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            val monthStart = calendar.timeInMillis
            calendar.add(Calendar.MONTH, 1)
            val monthEnd = calendar.timeInMillis

            sessions.count { session ->
                session.date >= monthStart && session.date < monthEnd
            }.toFloat()
        }.reversed()

        return Stats(daily, weekly, monthly)
    }
}

data class ActivityState(
    val habits: List<Habit> = emptyList(),
    val completedHabits: Int = 0,
    val totalHabits: Int = 0,
    val dailyStats: List<Float> = emptyList(),
    val weeklyStats: List<Float> = emptyList(),
    val monthlyStats: List<Float> = emptyList()
)

data class Stats(
    val daily: List<Float>,
    val weekly: List<Float>,
    val monthly: List<Float>
)