package com.example.jiralogger.presentation.work_log_add_edit

import androidx.compose.ui.focus.FocusState
import java.util.*

sealed class AddEditWorkLogEvent {
    data class IssueChosen(val issueId: String) : AddEditWorkLogEvent()
    data class ChangeIssueFocus(val focusState: FocusState) : AddEditWorkLogEvent()

    data class EnteredDescription(val value: String) : AddEditWorkLogEvent()
    data class ChangedDescriptionFocus(val focusState: FocusState) : AddEditWorkLogEvent()

    data class DateChosen(val value: Date) : AddEditWorkLogEvent()
    data class ChangedDateFocus(val focusState: FocusState) : AddEditWorkLogEvent()

    data class EnteredTimeSpent(val value: String) : AddEditWorkLogEvent()
    data class ChangedTimeSpentFocus(val focusState: FocusState) : AddEditWorkLogEvent()

    data class EnteredTimeSpentSec(val value: Int) : AddEditWorkLogEvent()
    data class ChangedTimeSpentSecFocus(val focusState: FocusState) : AddEditWorkLogEvent()

    //    object Edit : AddEditWorkLogEvent()
//    object Cancel : AddEditWorkLogEvent()
    object Save : AddEditWorkLogEvent()
}