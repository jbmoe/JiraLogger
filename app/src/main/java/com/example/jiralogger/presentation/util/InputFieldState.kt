package com.example.jiralogger.presentation.util

data class InputFieldState<T>(
    val value: T,
    val hint: String = "",
    val isHintVisible: Boolean = true,
    val error: String = "",
    val isErrorVisible: Boolean = false
)