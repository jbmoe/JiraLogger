package com.example.jiralogger.presentation.work_log_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.domain.use_case.work_log.GetWorkLogs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WorkLogListViewModel @Inject constructor(
    private val getWorkLogsUseCase: GetWorkLogs
) : ViewModel() {
    private val _state = mutableStateOf(WorkLogListState())
    val state: State<WorkLogListState> = _state

    private var _refreshAction: (() -> Unit)? = null

    init {
        getWorkLogs()
    }

    fun onEvent(event: WorkLogsEvent) {
        when (event) {
            is WorkLogsEvent.Refresh -> {
                refresh()
            }
        }
    }

    private fun refresh() {
        _refreshAction?.invoke()
    }

    private fun getWorkLogs() {
        getWorkLogsUseCase().onEach { result ->
            _state.value = WorkLogListState(result)
        }.launchIn(viewModelScope)
        _refreshAction = { getWorkLogs() }
    }
}