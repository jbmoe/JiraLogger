package com.example.jiralogger.presentation.work_log_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.use_case.work_log.WorkLogUseCases
import com.example.jiralogger.domain.util.OrderType
import com.example.jiralogger.domain.util.WorkLogGroupBy
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

    private var recentlyDeletedLog: WorkLog? = null

    private var getLogsJob: Job? = null

    private var _refreshAction: (() -> Unit)? = null

    val groupBys = listOf(
        WorkLogGroupBy.Date(OrderType.Ascending),
        WorkLogGroupBy.Issue(OrderType.Ascending)
    )

    init {
        getWorkLogs(WorkLogGroupBy.Date(OrderType.Ascending))
        initDB()
    }

    private fun initDB() {
        if (state.value.itemMap.isEmpty()) {
            viewModelScope.launch {
                TestData.WORK_LOG_TEST_DATA.forEach {
                    useCases.insertWorkLog(it)
                }
            }
        }
    }

    fun onEvent(event: WorkLogsEvent) {
        val q = event.hashCode()
        when (event) {
            is WorkLogsEvent.Refresh -> {
                refresh()
            }
            is WorkLogsEvent.DeleteLog -> {
                deleteLog(event.log)
            }
            is WorkLogsEvent.RestoreLog -> {
                restoreLog()
            }
            is WorkLogsEvent.GroupBy -> {
                if (state.value.groupBy::class != event.groupBy::class) {
                    getWorkLogs(event.groupBy)
                }
            }
            is WorkLogsEvent.ToggleOrderType -> {
                Log.d("DEBUG", "on event ${state.value.groupBy}")
                val orderType: OrderType =
                    if (state.value.groupBy.orderType == OrderType.Ascending) {
                        OrderType.Descending
                    } else {
                        OrderType.Ascending
                    }
                getWorkLogs(state.value.groupBy.copy(orderType))
            }
            is WorkLogsEvent.ToggleGroupBySelection -> {
                _state.value = _state.value.copy(
                    groupByIsVisible = !_state.value.groupByIsVisible
                )
            }
        }
    }

    private fun restoreLog() {
        viewModelScope.launch {
            useCases.insertWorkLog(recentlyDeletedLog ?: return@launch)
            recentlyDeletedLog = null
        }
    }

    private fun deleteLog(log: WorkLog) {
        viewModelScope.launch {
            useCases.deleteWorkLog(log)
            recentlyDeletedLog = log
        }
    }

    private fun refresh() {
        _refreshAction?.invoke()
    }

    private fun getWorkLogs(groupBy: WorkLogGroupBy) {
        getLogsJob?.cancel()
        getLogsJob = useCases.getWorkLogs(groupBy).onEach { result ->
            _state.value = _state.value.copy(
                itemMap = result,
                groupBy = groupBy
            )
        }.launchIn(viewModelScope)
        _refreshAction = { getWorkLogs(groupBy) }
    }
}