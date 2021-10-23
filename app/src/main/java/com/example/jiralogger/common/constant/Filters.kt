package com.example.jiralogger.common.constant

object Filters {
    const val FILTER_ASSIGNED_TO_ME =
        "assignee%20in%20(currentUser())%20ORDER%20BY%20assignee%20ASC%2C%20lastViewed%20DESC"
    const val REPORTED_BY_ME = "reporter%20in%20(currentUser())"
    const val FOLLOWING = ""
    const val LAST_SEEN = "ORDER%20BY%20lastViewed%20DESC"
}