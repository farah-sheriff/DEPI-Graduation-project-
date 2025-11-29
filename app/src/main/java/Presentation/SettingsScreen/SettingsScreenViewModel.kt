package Presentation.SettingsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    // =============================
    // STATE MANAGEMENT
    // =============================

    private val _soundEnabled = MutableStateFlow(true)
    val soundEnabled: StateFlow<Boolean> get() = _soundEnabled

    // Event channel for navigation or actions
    private val _uiEvent = MutableStateFlow<SettingsUiEvent?>(null)
    val uiEvent: StateFlow<SettingsUiEvent?> get() = _uiEvent

    // =============================
    // BUSINESS LOGIC
    // =============================

    fun toggleSound(enabled: Boolean) {
        _soundEnabled.value = enabled
    }

    fun onHomeClick() = sendEvent(SettingsUiEvent.NavigateHome)

    fun onStatsClick() = sendEvent(SettingsUiEvent.NavigateStats)

    fun onSettingsClick() = sendEvent(SettingsUiEvent.ReloadSettings)

    private fun sendEvent(event: SettingsUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
}

// =============================
// EVENT MODEL
// =============================
sealed class SettingsUiEvent {
    object NavigateHome : SettingsUiEvent()
    object NavigateStats : SettingsUiEvent()
    object ReloadSettings : SettingsUiEvent()
}
