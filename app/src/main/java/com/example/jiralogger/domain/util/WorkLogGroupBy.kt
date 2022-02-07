package com.example.jiralogger.domain.util

sealed class WorkLogGroupBy(override val name: String) : Tabable {
    object Issue : WorkLogGroupBy(name = "Issue")
    object Date : WorkLogGroupBy(name = "Date")
}