package com.example.jiralogger.presentation.work_log_list

import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.util.OrderType
import com.example.jiralogger.domain.util.WorkLogGroupBy

data class WorkLogListState(
    val itemMap: Map<String, List<WorkLog>> = emptyMap(),
    val isLoading: Boolean = false,
    val error: String = "",
    val groupBy: WorkLogGroupBy = WorkLogGroupBy.Date(OrderType.Descending)
)