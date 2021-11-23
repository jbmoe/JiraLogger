package com.example.jiralogger.presentation.work_log_add_edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.domain.model.InvalidLogException
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.use_case.work_log.GetWorkLog
import com.example.jiralogger.domain.use_case.work_log.InsertWorkLog
import com.example.jiralogger.presentation.components.NumberPickerEvent
import com.example.jiralogger.presentation.util.InputFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditLogViewModel @Inject constructor(
    private val getWorkLogUseCase: GetWorkLog,
    private val insertWorkLogUseCase: InsertWorkLog,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _issueId = mutableStateOf(
        InputFieldState(
            value = "",
            label = "Issue",
            placeholder = "Search issues..."
        )
    )
    val issueId: State<InputFieldState<String>> = _issueId

    private val _description = mutableStateOf(
        InputFieldState(
            value = "",
            label = "Comment",
            placeholder = "Describe the work you've been doing"
        )
    )
    val description: State<InputFieldState<String>> = _description

    private val _date = mutableStateOf(System.currentTimeMillis())
    val date: State<Long> = _date

    private val _timeSpentSec = mutableStateOf(0)
    private val _hoursSpent = mutableStateOf(0)
    val hoursSpent: State<Int> = _hoursSpent

    private val _minutesSpent = mutableStateOf(0)
    val minutesSpent: State<Int> = _minutesSpent

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentLogId: Int? = null

    private val hours = (0..99).toList()
    private val minutes = listOf(0, 15, 30, 45)


    init {
        savedStateHandle.get<Int>(Constants.PARAM_WORK_LOG_ID)?.let { logId ->
            if (logId != -1) {
                getWorkLog(logId)
            } else {
                savedStateHandle.get<String>(Constants.PARAM_ISSUE_KEY)?.let { issueId ->
                    _issueId.value = _issueId.value.copy(
                        value = issueId
                    )
                }
            }
        }
    }

    fun onEvent(event: AddEditWorkLogEvent) {
        when (event) {
            is AddEditWorkLogEvent.IssueChosen -> {
                _issueId.value = _issueId.value.copy(
                    value = event.issueId
                )
            }
            is AddEditWorkLogEvent.EnteredDescription -> {
                _description.value = _description.value.copy(
                    value = event.value
                )
            }
            is AddEditWorkLogEvent.DateChosen -> {
                _date.value = event.value
            }
            is AddEditWorkLogEvent.HoursChanged -> {
                handleHoursEvent(event.event)
            }
            is AddEditWorkLogEvent.MinutesChanged -> {
                handleMinutesEvent(event.event)
            }
            is AddEditWorkLogEvent.Save -> {
                save()
            }
        }
    }

    private fun handleHoursEvent(event: NumberPickerEvent) {
        if (event == NumberPickerEvent.Increase)
            if (_hoursSpent.value == hours.last())
                _hoursSpent.value = hours.first()
            else
                _hoursSpent.value++
        else
            if (_hoursSpent.value == hours.first())
                _hoursSpent.value = hours.last()
            else
                _hoursSpent.value--
        updateTimeSpentSeconds()
    }

    private fun handleMinutesEvent(event: NumberPickerEvent) {
        if (event == NumberPickerEvent.Increase)
            if (_minutesSpent.value == minutes.last())
                _minutesSpent.value = minutes.first()
            else
                _minutesSpent.value = minutes[minutes.indexOf(_minutesSpent.value) + 1]
        else
            if (_minutesSpent.value == minutes.first())
                _minutesSpent.value = minutes.last()
            else
                _minutesSpent.value = minutes[minutes.indexOf(_minutesSpent.value) - 1]
        updateTimeSpentSeconds()
    }

    private fun updateTimeSpentSeconds() {
        _timeSpentSec.value = (_hoursSpent.value * 60 * 60) + (_minutesSpent.value * 60)
    }

    private fun save() {
        viewModelScope.launch {
            try {
                insertWorkLogUseCase(
                    WorkLog(
                        id = currentLogId,
                        issueId = _issueId.value.value,
                        comment = if (_description.value.value.isNotBlank()) _description.value.value else "Working on issue ${_issueId.value.value}",
                        dateWorked = _date.value,
                        timeSpent = getTimeSpent(),
                        timeSpentSeconds = _timeSpentSec.value,
                        userId = "JEM"
                    )
                )
                _eventFlow.emit(UiEvent.SaveLog)
            } catch (e: InvalidLogException) {
                _eventFlow.emit(
                    UiEvent.ShowSnackbar(
                        message = e.message ?: "Couldn't save log"
                    )
                )
            }
        }
    }

    private fun getTimeSpent(): String {
        var toReturn = ""
        val hours = _hoursSpent.value
        val minutes = _minutesSpent.value

        if (hours != 0) {
            toReturn += "${hours}h "
        }
        if (minutes != 0) {
            toReturn += "${minutes}m"
        }

        return toReturn
    }

    private fun getWorkLog(logId: Int) {
        viewModelScope.launch {
            getWorkLogUseCase(logId)?.also { log ->
                currentLogId = log.id
                _issueId.value = _issueId.value.copy(
                    value = log.issueId
                )
                _description.value = _description.value.copy(
                    value = log.comment
                )
                _date.value = log.dateWorked
                _timeSpentSec.value = log.timeSpentSeconds
                val hours = _timeSpentSec.value / 3600
                val minutes = _timeSpentSec.value.mod(3600) / 60
                _hoursSpent.value = hours
                _minutesSpent.value = minutes
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveLog : UiEvent()
    }
}