package com.example.jiralogger.presentation.work_log

import com.example.jiralogger.presentation.components.NumberPickerEvent

sealed class WorkLogEvent {
    data class IssueChosen(val issueId: String) : WorkLogEvent()

    data class EnteredDescription(val value: String) : WorkLogEvent()

    data class DateChosen(val value: Long) : WorkLogEvent()

    data class HoursChanged(val event: NumberPickerEvent) : WorkLogEvent()

    data class MinutesChanged(val event: NumberPickerEvent) : WorkLogEvent()

    object Save : WorkLogEvent()
}