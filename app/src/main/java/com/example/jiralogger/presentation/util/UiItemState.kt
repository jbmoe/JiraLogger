package com.example.jiralogger.presentation.util

interface UiItemState {
    val item: Any?
    val error: String
    val isLoading: Boolean
}