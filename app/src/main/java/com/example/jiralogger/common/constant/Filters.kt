package com.example.jiralogger.common.constant

object Filters {
    const val ASSIGNED_TO_ME =
        "assignee in (currentUser()) order by updated DESC"
    const val REPORTED_BY_ME = "reporter in (currentUser())"
    const val FOLLOWING = ""
    const val LAST_SEEN = "ORDER BY lastViewed DESC"
}