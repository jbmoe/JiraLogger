package com.example.jiralogger.presentation.issue_list

import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.util.IssueFilter

data class IssueListState(
    val isLoading: Boolean = false,
    val issues: List<Issue?> = emptyList(),
    val error: String = "",
    val issueFilter: IssueFilter = IssueFilter.Assigned
)
