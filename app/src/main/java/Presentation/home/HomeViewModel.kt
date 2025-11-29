package Presentation.home


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

}

data class HomeState(
    val habits: List<String> = emptyList()  // لحد ما تعملوا الداتا بيس
)
