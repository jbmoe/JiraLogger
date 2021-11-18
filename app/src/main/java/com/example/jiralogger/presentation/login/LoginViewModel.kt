package com.example.jiralogger.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.domain.model.UserCredential
import com.example.jiralogger.domain.use_case.user_credential.Login
import com.example.jiralogger.domain.use_case.user_credential.UserCredentialUseCases
import com.example.jiralogger.presentation.util.InputFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userCredentialUseCases: UserCredentialUseCases,
    private val loginUseCase: Login
) : ViewModel() {
    private val _username = mutableStateOf(
        InputFieldState(
            value = "",
            label = "Username",
            placeholder = "Your Jira username"
        )
    )
    val username: State<InputFieldState<String>> = _username

    private val _password = mutableStateOf(
        InputFieldState(
            value = "",
            label = "Password",
            placeholder = "Your Jira password"
        )
    )
    val password: State<InputFieldState<String>> = _password

    init {

    }

    fun onEvent(event: LoginEvent): Boolean? {
        viewModelScope.launch {
            when (event) {
                is LoginEvent.UsernameEntered -> _username.value.copy(
                    value = event.value
                ).also { _username.value = it }
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
    }

    private suspend fun login(): Boolean {
        val succes = loginUseCase(username.value.value, password.value.value)

        return if (succes) {
            true
        } else {
            _username.value = _username.value.copy(
                isError = true
            )
            _password.value = _password.value.copy(
                isError = true
            )
            false
        }
    }
}