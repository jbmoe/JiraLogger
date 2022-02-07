package com.example.jiralogger.presentation.work_log

import com.example.jiralogger.presentation.util.InputFieldState

data class WorkLogState(
    val issueId: InputFieldState<String> = InputFieldState(
        value = "",
        label = "Issue",
        placeholder = "Search issues..."
    ),
    val description: InputFieldState<String> = InputFieldState(
        value = "",
        label = "Comment",
        placeholder = "Describe the work you've been doing"
    ),
    val date: InputFieldState<Long> = InputFieldState(
        value = System.currentTimeMillis(),
        label = "Date",
        placeholder = "Choose a date"
    ),
    val timeSpentSec: InputFieldState<Int> = InputFieldState(
        value = 0
    ),
    val hoursSpent: Int = 0,
    val minutesSpent: Int = 0,
)