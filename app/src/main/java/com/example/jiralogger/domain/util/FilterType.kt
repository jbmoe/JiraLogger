package com.example.jiralogger.domain.util

import com.example.jiralogger.common.constant.Filters

sealed class FilterType(val filter: String) {
    class Assigned : FilterType(Filters.ASSIGNED_TO_ME)
    class Seen : FilterType(Filters.LAST_SEEN)
    class EV : FilterType(Filters.EV)
}