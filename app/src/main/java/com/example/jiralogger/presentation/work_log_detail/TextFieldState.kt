package com.example.jiralogger.presentation.work_log_detail

//data class TextFieldState(
//    val text: String = "",
//    val hint: String = "",
//    val isHintVisible: Boolean = false
//)

data class InputFieldState<T>(
    val value: T? = null,
    val hint: String = "",
    val isHintVisible: Boolean = true
)