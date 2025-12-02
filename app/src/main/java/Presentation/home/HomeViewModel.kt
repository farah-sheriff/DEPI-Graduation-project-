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

    // الحالة الرئيسية للشاشة، تحتوي على قائمة العادات
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadHabits()
        loadUserName()
    }

    // تحميل العادات من قاعدة البيانات
    private fun loadHabits() {
        viewModelScope.launch {
            repository.getHabits().collect { habits ->
                _state.value = _state.value.copy(habits = habits)
            }
        }
    }

    // تحميل اسم المستخدم
    private fun loadUserName() {
        viewModelScope.launch {
            userRepository.getUser().collect { user ->
                _state.value = _state.value.copy(userName = user?.name ?: "")
            }
        }
    }

    // دالة لإضافة Habit جديد
    suspend fun addHabit(habit: Habit): Long {
        return repository.addHabit(habit)
    }

    // دالة للحصول على Habit بواسطة ID
    suspend fun getHabitById(id: Long): Habit? {
        return repository.getHabitById(id)
    }

    // دالة لحذف Habit
    suspend fun deleteHabit(habit: Habit) {
        repository.deleteHabit(habit)
    }

    // دالة لتحديث Habit
    suspend fun updateHabit(habit: Habit) {
        repository.updateHabit(habit)
    }
}

// حالة الشاشة
data class HomeState(
    val habits: List<Habit> = emptyList(), // قائمة العادات
    val userName: String = "" // اسم المستخدم
)
