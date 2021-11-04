package com.example.jiralogger.presentation.work_log_list

import com.example.jiralogger.domain.model.WorkLog

data class WorkLogListState(
    val items: Map<Long, List<WorkLog>> = emptyMap(),
    val isLoading: Boolean = false,
    val error: String = ""
)