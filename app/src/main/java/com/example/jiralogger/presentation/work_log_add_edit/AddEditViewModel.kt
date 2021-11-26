package com.example.jiralogger.presentation.work_log_add_edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.domain.model.InvalidLogException
import com.example.jiralogger.domain.model.LogError
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.use_case.work_log.GetWorkLog
import com.example.jiralogger.domain.use_case.work_log.InsertWorkLog
import com.example.jiralogger.presentation.components.NumberPickerEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val getWorkLogUseCase: GetWorkLog,
    private val insertWorkLogUseCase: InsertWorkLog,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(AddEditState())
    val state: State<AddEditState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentLogId: Int? = null

    private val hours = (0..99).toList()
    private val minutes = listOf(0, 15, 30, 45)


    init {
        savedStateHandle.get<Int>(Constants.PARAM_WORK_LOG_ID)?.let { logId ->
            if (logId != -1) {
                getWorkLog(logId)
            }
        }
        savedStateHandle.get<String>(Constants.PARAM_ISSUE_KEY)?.let { issueId ->
            _state.value = _state.value.copy(
                issueId = _state.value.issueId.copy(value = issueId)
            )
        }
    }

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.IssueChosen -> {
                changeIssue(event.issueId)
            }
            is AddEditEvent.EnteredDescription -> {
                _state.value = _state.value.copy(
                    description = _state.value.description.copy(value = event.value)
                )
            }
            is AddEditEvent.DateChosen -> {
                _state.value = _state.value.copy(
                    date = _state.value.date.copy(value = event.value, isError = false),
                )
            }
            is AddEditEvent.HoursChanged -> {
                handleHoursEvent(event.event)
            }
            is AddEditEvent.MinutesChanged -> {
                handleMinutesEvent(event.event)
            }
            is AddEditEvent.Save -> {
                save()
            }
        }
    }

    private fun changeIssue(issueId: String) {
        if (_state.value.description.value.isNotBlank()) {
            _state.value = _state.value.copy(
                description = _state.value.description.copy(
                    value = _state.value.description.value.replace(
                        _state.value.issueId.value,
                        issueId
                    )
                )
            )
        }
        _state.value = _state.value.copy(
            issueId = _state.value.issueId.copy(value = issueId, isError = false),
        )
    }

    private fun handleHoursEvent(event: NumberPickerEvent) {
        if (event == NumberPickerEvent.Increase)
            if (_state.value.hoursSpent == hours.last())
                _state.value = _state.value.copy(hoursSpent = hours.first())
            else
                _state.value = _state.value.copy(hoursSpent = _state.value.hoursSpent + 1)
        else
            if (_state.value.hoursSpent == hours.first())
                _state.value = _state.value.copy(hoursSpent = hours.last())
            else
                _state.value = _state.value.copy(hoursSpent = _state.value.hoursSpent - 1)
        updateTimeSpentSeconds()
    }

    private fun handleMinutesEvent(event: NumberPickerEvent) {
        if (event == NumberPickerEvent.Increase)
            if (_state.value.minutesSpent == minutes.last())
                _state.value = _state.value.copy(minutesSpent = minutes.first())
            else
                _state.value = _state.value.copy(minutesSpent = _state.value.minutesSpent + 1)
        else
            if (_state.value.minutesSpent == minutes.first())
                _state.value = _state.value.copy(minutesSpent = minutes.last())
            else
                _state.value = _state.value.copy(minutesSpent = _state.value.minutesSpent - 1)
        updateTimeSpentSeconds()
    }

    private fun updateTimeSpentSeconds() {
        _state.value = _state.value.copy(
            timeSpentSec = _state.value.timeSpentSec.copy(
                value = (_state.value.hoursSpent * 60 * 60) + (_state.value.minutesSpent * 60),
                isError = false
            )
        )
    }

    private fun save() {
        viewModelScope.launch {
            try {
                insertWorkLogUseCase(
                    WorkLog(
                        id = currentLogId,
                        issueId = _state.value.issueId.value,
                        comment = if (_state.value.description.value.isNotBlank()) _state.value.description.value else "Working on issue ${_state.value.issueId.value}",
                        dateWorked = _state.value.date.value,
                        timeSpent = getTimeSpent(),
                        timeSpentSeconds = _state.value.timeSpentSec.value,
                        userId = TestData.USER
                    )
                )
                _eventFlow.emit(UiEvent.SaveLog)
            } catch (e: InvalidLogException) {
                when (e.err) {
                    LogError.IssueID -> {
                        _state.value = _state.value.copy(
                            issueId = _state.value.issueId.copy(isError = true)
                        )
                    }
                    LogError.Date -> {
                        _state.value = _state.value.copy(
                            date = _state.value.date.copy(isError = true)
                        )
                    }
                    LogError.TimeSpent -> {
                        _state.value = _state.value.copy(
                            timeSpentSec = _state.value.timeSpentSec.copy(isError = true)
                        )
                    }
                }
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
        val hours = _state.value.hoursSpent
        val minutes = _state.value.minutesSpent

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
                _state.value = _state.value.copy(
                    issueId = _state.value.issueId.copy(value = log.issueId),
                    description = _state.value.description.copy(value = log.comment),
                    date = _state.value.date.copy(value = log.dateWorked),
                    timeSpentSec = _state.value.timeSpentSec.copy(value = log.timeSpentSeconds),
                    minutesSpent = log.timeSpentSeconds.mod(3600) / 60,
                    hoursSpent = log.timeSpentSeconds / 3600
                )
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveLog : UiEvent()
    }
}