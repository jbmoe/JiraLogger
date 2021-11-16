package com.example.jiralogger.common.constant

object Filters {
    const val ASSIGNED_TO_ME =
        "assignee in (currentUser()) and status != Closed order by updated DESC"
    const val REPORTED_BY_ME = "reporter in (currentUser())"
    const val WATCHING = "watcher = currentUser()"
    const val EV = "project = EV ORDER BY key ASC"
    const val LAST_SEEN = "ORDER BY lastViewed DESC"
    const val SEARCH_PREFIX = "text ~ "
}