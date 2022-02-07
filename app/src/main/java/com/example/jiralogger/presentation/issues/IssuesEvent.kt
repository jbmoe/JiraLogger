package com.example.jiralogger.presentation.issues

import com.example.jiralogger.domain.util.IssueFilter

sealed interface IssuesEvent {
    data class OnFilterChanged(val filter: IssueFilter) : IssuesEvent
    data class OnSearch(val filter: IssueFilter) : IssuesEvent
    object OnRefresh : IssuesEvent
}