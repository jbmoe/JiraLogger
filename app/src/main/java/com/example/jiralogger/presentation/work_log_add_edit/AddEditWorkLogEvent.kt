package com.example.jiralogger.presentation.work_log_add_edit

import androidx.compose.ui.focus.FocusState
import com.example.jiralogger.presentation.components.NumberPickerEvent

sealed class AddEditWorkLogEvent {
    data class IssueChosen(val issueId: String) : AddEditWorkLogEvent()

    data class EnteredDescription(val value: String) : AddEditWorkLogEvent()

    data class DateChosen(val value: Long) : AddEditWorkLogEvent()

    data class HoursChanged(val event: NumberPickerEvent) : AddEditWorkLogEvent()

    data class MinutesChanged(val event: NumberPickerEvent) : AddEditWorkLogEvent()

    object Save : AddEditWorkLogEvent()
}