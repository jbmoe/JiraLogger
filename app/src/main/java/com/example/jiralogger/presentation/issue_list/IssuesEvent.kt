package com.example.jiralogger.presentation.issue_list

import com.example.jiralogger.domain.util.IssueFilter

sealed interface IssuesEvent {
    data class Filter(val filter: IssueFilter) : IssuesEvent
    data class Search(val filter: IssueFilter) : IssuesEvent
    object Refresh : IssuesEvent
    object ToggleSearchVisibility : IssuesEvent
}