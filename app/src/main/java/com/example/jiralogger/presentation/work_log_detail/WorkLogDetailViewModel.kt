package com.example.jiralogger.presentation.work_log_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.common.Resource
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.domain.use_case.work_log.GetWorkLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkLogDetailViewModel @Inject constructor(
    private val getWorkLogUseCase: GetWorkLog,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(WorkLogDetailState())
    val state: State<WorkLogDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_WORK_LOG_ID)?.let { id ->
            getWorkLog(id.toInt())
        }
    }

    fun onEvent(event: WorkLogEvent) {
        when (event) {
            is WorkLogEvent.Edit -> {
                _state.value = _state.value.copy(isEditing = true)
            }
            is WorkLogEvent.Save -> {
                _state.value = _state.value.copy(isEditing = false)
            }
            is WorkLogEvent.Cancel -> {
                _state.value = _state.value.copy(isEditing = false)
            }
        }
    }

    private fun getWorkLog(id: Int) {
        viewModelScope.launch {
            getWorkLogUseCase(id).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = WorkLogDetailState(item = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = WorkLogDetailState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = WorkLogDetailState(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}
