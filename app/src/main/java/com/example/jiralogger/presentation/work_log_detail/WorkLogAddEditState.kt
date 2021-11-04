package com.example.jiralogger.presentation.work_log_detail

import com.example.jiralogger.domain.model.WorkLog

data class WorkLogDetailState(
    val item: WorkLog? = null,
    val error: String = "",
    val isLoading: Boolean = false,
    val isEditing: Boolean = false
)