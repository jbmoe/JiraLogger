package com.example.jiralogger.domain.util

import com.example.jiralogger.common.constant.Filters

/**
 * Inner objects prefixed with letter as iterating will sort them alphabetically
 */
sealed class IssueFilter(val value: String, val name: String) {
    object A_Assigned : IssueFilter(Filters.ASSIGNED_TO_ME, "Assigned")
    object B_Seen : IssueFilter(Filters.LAST_SEEN, "Seen")
    object C_EV : IssueFilter(Filters.EV, "EV")
//
//    val values = listOf(Assigned, Seen, EV)
//    fun forEach(callback: (IssueFilter) -> Unit) {
//        values.forEach { callback(it) }
//    }
}