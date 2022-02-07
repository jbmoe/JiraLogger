package com.example.jiralogger.presentation.work_logs

import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.util.WorkLogGroupBy

data class WorkLogsState(
    val itemMap: Map<String, List<WorkLog>> = emptyMap(),
    val totalMap: Map<String, Int> = emptyMap(),
    val isLoading: Boolean = false,
    val error: String = "",
    val groupByIsVisible: Boolean = true,
    val groupBy: WorkLogGroupBy = WorkLogGroupBy.Date
)