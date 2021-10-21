package com.example.jiralogger.presentation.issue_list

import com.example.jiralogger.domain.model.Issue

data class IssueListState(
    val isLoading: Boolean = false,
    val issues: List<Issue?> = emptyList(),
    val error: String = ""
)
