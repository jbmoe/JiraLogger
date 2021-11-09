package com.example.jiralogger.presentation.work_log_add_edit

data class InputFieldState<T>(
    val value: T,
    val hint: String = "",
    val isHintVisible: Boolean = true
)