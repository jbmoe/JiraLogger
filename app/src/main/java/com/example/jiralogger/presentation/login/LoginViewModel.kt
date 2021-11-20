package com.example.jiralogger.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.domain.model.UserCredential
import com.example.jiralogger.domain.use_case.user_credential.Login
import com.example.jiralogger.domain.use_case.user_credential.UserCredentialUseCases
import com.example.jiralogger.presentation.util.InputFieldState
import com.example.jiralogger.presentation.work_log_add_edit.AddEditLogViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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
            placeholder = "Your Jira username",
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

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvent) {
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

    private suspend fun login() {
        val succes = loginUseCase(username.value.value, password.value.value)

        if (succes) {
            _username.value = _username.value.copy(
                isError = false
            )
            _password.value = _password.value.copy(
                isError = false
            )
            _eventFlow.emit(UiEvent.LoginSuccess)
        } else {
            _username.value = _username.value.copy(
                isError = true
            )
            _password.value = _password.value.copy(
                isError = true
            )
            _eventFlow.emit(UiEvent.LoginFailed)
        }
    }

    sealed class UiEvent {
        object LoginSuccess : UiEvent()
        object LoginFailed : UiEvent()
    }
}