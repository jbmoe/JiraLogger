package com.example.jiralogger.domain.util

import com.example.jiralogger.common.constant.Filters

sealed class IssueFilter(val value: String) {
    object Assigned : IssueFilter(Filters.ASSIGNED_TO_ME)
    object Seen : IssueFilter(Filters.LAST_SEEN)
    object EV : IssueFilter(Filters.EV)
}