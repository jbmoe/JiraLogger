package com.example.jiralogger.presentation.work_log_list

sealed interface WorkLogsEvent {
    object Refresh : WorkLogsEvent
}
