package com.example.jiralogger.presentation.issues

import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.util.IssueFilter

data class IssuesState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val items: List<Issue> = emptyList(),
    val error: String = "",
    val issueFilter: IssueFilter = IssueFilter.Assigned,
)
