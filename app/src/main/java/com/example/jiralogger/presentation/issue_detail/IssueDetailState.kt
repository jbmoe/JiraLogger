package com.example.jiralogger.presentation.issue_detail

import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.presentation.util.UiItemState

data class IssueDetailState(
    override val isLoading: Boolean = false,
    override val item: Issue? = null,
    override val error: String = ""
) : UiItemState
