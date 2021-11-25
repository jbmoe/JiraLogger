package com.example.jiralogger.presentation.work_log_add_edit

import com.example.jiralogger.presentation.components.NumberPickerEvent

sealed class AddEditEvent {
    data class IssueChosen(val issueId: String) : AddEditEvent()

    data class EnteredDescription(val value: String) : AddEditEvent()

    data class DateChosen(val value: Long) : AddEditEvent()

    data class HoursChanged(val event: NumberPickerEvent) : AddEditEvent()

    data class MinutesChanged(val event: NumberPickerEvent) : AddEditEvent()

    object Save : AddEditEvent()
}