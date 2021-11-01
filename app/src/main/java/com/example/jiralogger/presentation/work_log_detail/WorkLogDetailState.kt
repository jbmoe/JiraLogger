package com.example.jiralogger.presentation.work_log_detail

import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.presentation.util.UiItemState

data class WorkLogDetailState(
    override val item: WorkLog? = null,
    override val error: String = "",
    override val isLoading: Boolean = false,
    val isEditing: Boolean = false
) : UiItemState