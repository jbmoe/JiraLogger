package com.example.jiralogger.presentation.issue_detail

import com.example.jiralogger.domain.model.Issue

data class IssueDetailState(
    val isLoading: Boolean = false,
    val item: Issue? = null,
    val error: String = ""
)
