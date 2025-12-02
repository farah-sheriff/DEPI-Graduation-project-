package com.example.habittracker.presentation.create_account


import Domain.Model.User
import Domain.Repository.UserRepository
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

class CreateAccountViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var name = mutableStateOf("")
    var surname = mutableStateOf("")
    var birthdate = mutableStateOf("")

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            userRepository.getUser().collect { user ->
                user?.let {
                    name.value = it.name
                    surname.value = it.surname
                    birthdate.value = it.birthdate
                }
            }
        }
    }

    fun isFormValid(): Boolean {
        return name.value.isNotBlank() &&
                surname.value.isNotBlank() &&
                birthdate.value.isNotBlank()
    }

    suspend fun saveUser() {
        if (isFormValid()) {
            userRepository.saveUser(
                User(
                    name = name.value,
                    surname = surname.value,
                    birthdate = birthdate.value
                )
            )
        }
    }
}