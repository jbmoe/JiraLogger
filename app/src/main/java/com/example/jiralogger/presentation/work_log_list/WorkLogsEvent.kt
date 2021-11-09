package com.example.jiralogger.presentation.work_log_list

import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.util.WorkLogOrder

sealed interface WorkLogsEvent {
    data class Order(val order: WorkLogOrder): WorkLogsEvent
    data class DeleteLog(val log: WorkLog): WorkLogsEvent
    object RestoreLog: WorkLogsEvent
    object ToggleOrderSelection: WorkLogsEvent
    object Refresh : WorkLogsEvent
}