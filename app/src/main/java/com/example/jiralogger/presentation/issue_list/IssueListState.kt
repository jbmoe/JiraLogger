package com.example.jiralogger.presentation.issue_list

import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.util.IssueFilter

data class IssueListState(
    val isLoading: Boolean = false,
    val items: List<Issue> = emptyList(),
    val itemMap: Map<Any, List<Any>> = emptyMap(),
    val error: String = "",
    val filterIsVisible: Boolean = true,
    val searchIsVisible: Boolean = false,
    val issueFilter: IssueFilter = IssueFilter.Assigned
)