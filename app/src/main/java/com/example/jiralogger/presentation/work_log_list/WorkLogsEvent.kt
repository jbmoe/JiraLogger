package com.example.jiralogger.presentation.work_log_list

import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.util.WorkLogGroupBy

sealed interface WorkLogsEvent {
    data class Order(val groupBy: WorkLogGroupBy): WorkLogsEvent
    data class DeleteLog(val log: WorkLog): WorkLogsEvent
    object RestoreLog: WorkLogsEvent
    object ToggleOrderSelection: WorkLogsEvent
    object Refresh : WorkLogsEvent
}