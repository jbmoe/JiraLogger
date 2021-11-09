package com.example.jiralogger.presentation.work_log_list

import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.util.OrderType
import com.example.jiralogger.domain.util.WorkLogOrder
import com.example.jiralogger.presentation.util.UiListState

data class WorkLogListState(
    override val items: List<WorkLog> = emptyList(),
    override val itemMap: Map<Any, List<Any>> = emptyMap(),
    override val isLoading: Boolean = false,
    override val error: String = "",
    val logOrder: WorkLogOrder = WorkLogOrder.Date(OrderType.Descending)
) : UiListState