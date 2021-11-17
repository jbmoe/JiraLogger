package com.example.jiralogger.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jiralogger.presentation.util.InputFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {
    private val _username = mutableStateOf(
        InputFieldState(
            value = "",
            hint = "Username"
        )
    )
    val username: State<InputFieldState<String>> = _username

    private val _password = mutableStateOf(
        InputFieldState(
            value = "",
            hint = "Password"
        )
    )
    val password: State<InputFieldState<String>> = _password

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UsernameEntered -> _username.value = _username.value.copy(
                value = event.value
            )
            is LoginEvent.PasswordEntered -> _password.value = _password.value.copy(
                value = event.value
            )
            is LoginEvent.ClearUsername -> _username.value = _username.value.copy(
                value = ""
            )
            is LoginEvent.ClearPassword -> _password.value = _password.value.copy(
                value = ""
            )
            is LoginEvent.Login -> login()
        }
    }

    private fun login() {

    }
}