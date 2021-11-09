package com.example.jiralogger.presentation.work_log_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.domain.use_case.work_log.WorkLogUseCases
import com.example.jiralogger.domain.util.OrderType
import com.example.jiralogger.domain.util.WorkLogOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkLogListViewModel @Inject constructor(
    private val useCases: WorkLogUseCases
) : ViewModel() {
    private val _state = mutableStateOf(WorkLogListState())
    val state: State<WorkLogListState> = _state

    private var getLogsJob: Job? = null

    private var _refreshAction: (() -> Unit)? = null

    init {
        getWorkLogs()
        initDB()
    }

    private fun initDB() {
        if (state.value.items.isEmpty()) {
            viewModelScope.launch {
                TestData.WORK_LOG_TEST_DATA.forEach {
                    useCases.insertWorkLog(it)
                }
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

    private fun getWorkLogs(order: WorkLogOrder = WorkLogOrder.Date(OrderType.Descending)) {
        getLogsJob?.cancel()
        getLogsJob = useCases.getWorkLogs(order).onEach { result ->
            _state.value = _state.value.copy(
                items = result,
                itemMap = result.groupBy {
                    it.dateWorked
                },
                logOrder = order
            )
        }.launchIn(viewModelScope)
        _refreshAction = { getWorkLogs() }
    }
}