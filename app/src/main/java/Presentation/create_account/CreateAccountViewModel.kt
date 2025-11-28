package com.example.habittracker.presentation.create_account

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CreateAccountViewModel : ViewModel() {

    var name = mutableStateOf("")
    var surname = mutableStateOf("")
    var birthdate = mutableStateOf("")

    fun isFormValid(): Boolean {
        return name.value.isNotBlank() &&
                surname.value.isNotBlank() &&
                birthdate.value.isNotBlank()
    }
}