package com.example.jiralogger.presentation.work_log_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.use_case.work_log.GetWorkLog
import com.example.jiralogger.domain.use_case.work_log.InsertWorkLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditLogViewModel @Inject constructor(
    private val getWorkLogUseCase: GetWorkLog,
    private val insertWorkLogUseCase: InsertWorkLog,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _issueId = mutableStateOf(
        InputFieldState<String>(
            hint = "Issue ID"
        )
    )
    val issueId: State<InputFieldState<String>> = _issueId

    private val _description = mutableStateOf(
        InputFieldState<String>(
            hint = "Describe the work you've been doing"
        )
    )
    val description: State<InputFieldState<String>> = _description

    private val _date = mutableStateOf(
        InputFieldState<Date>(
            hint = "The date you've been working"
        )
    )
    val date: State<InputFieldState<Date>> = _date

    private val _timeSpent = mutableStateOf(
        InputFieldState<String>(
            hint = "How much time you've spent"
        )
    )
    val timeSpent: State<InputFieldState<String>> = _timeSpent

    private val _timeSpentSec = mutableStateOf(
        InputFieldState<Int>(
            hint = "How much time you've spent"
        )
    )
    val timeSpentSec: State<InputFieldState<Int>> = _timeSpentSec

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentLogId: Int? = null

    init {
        savedStateHandle.get<Int>(Constants.PARAM_WORK_LOG_ID)?.let { logId ->
            if (logId != -1) {
                getWorkLog(logId)
            } else {
                savedStateHandle.get<String>(Constants.PARAM_ISSUE_KEY)?.let { issueId ->
                    _issueId.value = _issueId.value.copy(
                        value = issueId,
                        isHintVisible = false
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
            is AddEditWorkLogEvent.ChangeIssueFocus -> {
                _issueId.value = _issueId.value.copy(
                    isHintVisible = !event.focusState.isFocused && _issueId.value.value.isNullOrBlank()
                )
            }
            is AddEditWorkLogEvent.EnteredDescription -> {
                _description.value = _description.value.copy(
                    value = event.value
                )
            }
            is AddEditWorkLogEvent.ChangedDescriptionFocus -> {
                _description.value = _description.value.copy(
                    isHintVisible = !event.focusState.isFocused && _description.value.value.isNullOrBlank()
                )
            }
            is AddEditWorkLogEvent.DateChosen -> {
                _date.value = _date.value.copy(
                    value = event.value
                )
            }
            is AddEditWorkLogEvent.ChangedDateFocus -> {
                _date.value = _date.value.copy(
                    isHintVisible = !event.focusState.isFocused && _date.value.value == null
                )
            }
            is AddEditWorkLogEvent.EnteredTimeSpent -> {
                _timeSpent.value = _timeSpent.value.copy(
                    value = event.value
                )
            }
            is AddEditWorkLogEvent.ChangedTimeSpentFocus -> {
                _timeSpent.value = _timeSpent.value.copy(
                    isHintVisible = !event.focusState.isFocused && _timeSpent.value.value.isNullOrBlank()
                )
            }
            is AddEditWorkLogEvent.EnteredTimeSpentSec -> {
                _timeSpentSec.value = _timeSpentSec.value.copy(
                    value = event.value
                )
            }
            is AddEditWorkLogEvent.ChangedTimeSpentSecFocus -> {
                _timeSpentSec.value = _timeSpentSec.value.copy(
                    isHintVisible = !event.focusState.isFocused && _timeSpentSec.value.value == null
                )
            }
            is AddEditWorkLogEvent.Save -> {
                save()
            }
        }
    }

    private fun save() {
        viewModelScope.launch {
            try {
                insertWorkLogUseCase(
                    WorkLog(
                        issueId = _issueId.value.value!!,
                        comment = _description.value.value!!,
                        dateWorked = _date.value.value!!.time,
                        timeSpent = _timeSpent.value.value!!,
                        timeSpentSeconds = _timeSpentSec.value.value!!,
                        userId = "JEM"
                    )
                )
                _eventFlow.emit(UiEvent.SaveLog)
            } catch (e: Exception) {
                _eventFlow.emit(
                    UiEvent.ShowSnackbar(
                        message = e.message ?: "Couldn't save log"
                    )
                )
            }
        }
    }

    private fun getWorkLog(logId: Int) {
        viewModelScope.launch {
            getWorkLogUseCase(logId)?.also { log ->
                currentLogId = log.id
                _issueId.value = _issueId.value.copy(
                    value = log.issueId,
                    isHintVisible = false
                )
                _description.value = _description.value.copy(
                    value = log.comment,
                    isHintVisible = false
                )
                _date.value = _date.value.copy(
                    value = log.dateWorked?.let { Date(it) },
                    isHintVisible = false
                )
                _timeSpent.value = _timeSpent.value.copy(
                    value = log.timeSpent,
                    isHintVisible = false
                )
                _timeSpentSec.value = _timeSpentSec.value.copy(
                    value = log.timeSpentSeconds,
                    isHintVisible = false
                )
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveLog : UiEvent()
    }
}