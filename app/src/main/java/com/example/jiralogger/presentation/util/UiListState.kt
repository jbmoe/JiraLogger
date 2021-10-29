package com.example.jiralogger.presentation.util

interface UiListState {
    val items: List<Any?>
    val error: String
    val isLoading: Boolean
}