package com.example.jiralogger.domain.util

import com.example.jiralogger.common.constant.Filters

/**
 * Inner objects prefixed with letter as iterating
 * (IssueFilter::class.nestedSubClasses.forEach())
 * will sort them alphabetically
 */
sealed class IssueFilter(val value: String, override val name: String) : HasName {
    object Assigned : IssueFilter(Filters.ASSIGNED_TO_ME, "Assigned")
    object Seen : IssueFilter(Filters.LAST_SEEN, "Seen")
    object WATCHING : IssueFilter(Filters.WATCHING, "Watching")
    object EV : IssueFilter(Filters.EV, "EV")
    data class SEARCH(val search: String) : IssueFilter(Filters.SEARCH_PREFIX + "$search", "Search")
}