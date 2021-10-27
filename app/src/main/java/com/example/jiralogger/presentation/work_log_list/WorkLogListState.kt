package com.example.jiralogger.presentation.work_log_list

import com.example.jiralogger.domain.model.WorkLog

data class WorkLogListState(
    val workLogs: List<WorkLog> = emptyList()
)
