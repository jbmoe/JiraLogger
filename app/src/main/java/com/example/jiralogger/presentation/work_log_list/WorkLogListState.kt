package com.example.jiralogger.presentation.work_log_list

import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.presentation.util.UiListState

data class WorkLogListState(
    override val items: List<WorkLog> = emptyList(),
    override val isLoading: Boolean = false,
    override val error: String = "",
) : UiListState