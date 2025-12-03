package Presentation.home

import Domain.Repository.UserRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.domain.model.Habit
import com.example.habittracker.domain.repository.HabitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HabitRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadHabits()
        loadUserName()
    }

    private fun loadHabits() {
        viewModelScope.launch {
            repository.getHabits().collect { habits ->
                _state.value = _state.value.copy(habits = habits)
            }
        }
    }

    private fun loadUserName() {
        viewModelScope.launch {
            userRepository.getUser().collect { user ->
                _state.value = _state.value.copy(userName = user?.name ?: "")
            }
        }
    }

    suspend fun addHabit(habit: Habit): Long {
        return repository.addHabit(habit)
    }

    suspend fun getHabitById(id: Long): Habit? {
        return repository.getHabitById(id)
    }

    suspend fun deleteHabit(habit: Habit) {
        repository.deleteHabit(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        repository.updateHabit(habit)
    }
}

data class HomeState(
    val habits: List<Habit> = emptyList(),
    val userName: String = ""
)
