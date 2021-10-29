package com.example.jiralogger.presentation.issue_list

import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.util.IssueFilter
import com.example.jiralogger.presentation.util.UiListState

data class IssueListState(
    override val isLoading: Boolean = false,
    override val items: List<Issue?> = emptyList(),
    override val error: String = "",
    val issueFilter: IssueFilter = IssueFilter.Assigned
) : UiListState
