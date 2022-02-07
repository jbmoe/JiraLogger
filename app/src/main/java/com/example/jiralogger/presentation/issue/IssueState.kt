package com.example.jiralogger.presentation.issue

import com.example.jiralogger.domain.model.Issue

data class IssueState(
    val isLoading: Boolean = false,
    val item: Issue? = null,
    val error: String = ""
)
