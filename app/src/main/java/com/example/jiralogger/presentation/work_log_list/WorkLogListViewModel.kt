package com.example.jiralogger.presentation.work_log_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.common.Resource
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.use_case.work_log.GetWorkLogs
import com.example.jiralogger.domain.use_case.work_log.InsertWorkLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkLogListViewModel @Inject constructor(
    private val getWorkLogsUseCase: GetWorkLogs,
    private val insertWorkLog: InsertWorkLog
) : ViewModel() {
    private val _state = mutableStateOf(WorkLogListState())
    val state: State<WorkLogListState> = _state

    private var _refreshAction: (() -> Unit)? = null

    init {
        getWorkLogs()
        initDB()
    }

    fun mapLogs(logs: List<WorkLog>): Map<Long, List<WorkLog>> {
        return logs.groupBy {
            it.dateWorked!!
        }
    }

    private fun initDB() {
        if (state.value.items.isEmpty())
            viewModelScope.launch {
                TestData.WORK_LOG_TEST_DATA.forEach {
                    insertWorkLog(it)
                }
            }
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
            when (result) {
                is Resource.Success -> {
                    _state.value = WorkLogListState(
                        items = mapLogs(result.data ?: emptyList())
                    )
                }
                is Resource.Error -> {
                    _state.value =
                        WorkLogListState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                }
                is Resource.Loading -> {
                    _state.value = WorkLogListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
        _refreshAction = { getWorkLogs() }
    }
}