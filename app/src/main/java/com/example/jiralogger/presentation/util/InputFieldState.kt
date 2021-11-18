package com.example.jiralogger.presentation.util

data class InputFieldState<T>(
    val value: T,
    val label: String = "",
    val placeholder: String = "",
    val error: String = "",
    val isError: Boolean = false
)