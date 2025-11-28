package com.example.habittracker.presentation.gender

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GenderViewModel : ViewModel() {
    val selectedGender = mutableStateOf<String?>(null)

    fun select(gender: String) {
        selectedGender.value = gender
    }
}
