package com.example.jiralogger.domain.use_case.work_log

import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.repository.DbRepository
import com.example.jiralogger.domain.util.OrderType
import com.example.jiralogger.domain.util.WorkLogOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetWorkLogs @Inject constructor(
    private val repository: DbRepository
) {
    operator fun invoke(order: WorkLogOrder): Flow<List<WorkLog>> {
        return repository.getWorkLogs().map { logs ->
            when (order.orderType) {
                is OrderType.Ascending -> {
                    when (order) {
                        is WorkLogOrder.Date -> logs.sortedBy { it.dateWorked }
                        is WorkLogOrder.Issue -> logs.sortedBy { it.issueId }
                    }
                }
                is OrderType.Descending -> {
                    when (order) {
                        is WorkLogOrder.Date -> logs.sortedByDescending { it.dateWorked }
                        is WorkLogOrder.Issue -> logs.sortedByDescending { it.issueId }
                    }
                }
            }
        }
    }
}