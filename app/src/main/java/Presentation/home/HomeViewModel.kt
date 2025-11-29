package Presentation.home

import androidx.lifecycle.ViewModel
import com.example.habittracker.domain.model.Habit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    // الحالة الرئيسية للشاشة، تحتوي على قائمة العادات
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    // دالة لإضافة Habit جديد
    fun addHabit(habit: Habit) {
        _state.update { currentState ->
            currentState.copy(
                habits = currentState.habits + habit
            )
        }
    }
}

// حالة الشاشة
data class HomeState(
    val habits: List<Habit> = emptyList() // قائمة العادات
)
