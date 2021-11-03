package com.example.jiralogger.presentation.work_log_detail

sealed interface WorkLogEvent {
    object Edit : WorkLogEvent
    object Cancel : WorkLogEvent
    object Save : WorkLogEvent
}