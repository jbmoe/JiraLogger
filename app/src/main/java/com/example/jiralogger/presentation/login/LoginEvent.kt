package com.example.jiralogger.presentation.login

sealed class LoginEvent {
    data class UsernameEntered(val value: String) : LoginEvent()
    data class PasswordEntered(val value: String) : LoginEvent()
    object ClearUsername : LoginEvent()
    object ClearPassword : LoginEvent()
    object Login : LoginEvent()
}