package com.example.jiralogger.domain.use_case.work_log

import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.repository.DbRepository
import com.example.jiralogger.domain.util.OrderType
import com.example.jiralogger.domain.util.WorkLogGroupBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import javax.inject.Inject

class GetWorkLogs @Inject constructor(
    private val repository: DbRepository
) {
    private val date: (WorkLog) -> String = {
        val sdf = SimpleDateFormat("E d. MMMM yy")
        sdf.format(it.dateWorked)
    }

    private val issue: (WorkLog) -> String = {
        it.issueId
    }

    operator fun invoke(groupBy: WorkLogGroupBy): Flow<Map<String, List<WorkLog>>> {
        return repository.getWorkLogs().map { logs ->
            when (groupBy.orderType) {
                is OrderType.Ascending -> {
                    when (groupBy) {
                        is WorkLogGroupBy.Date -> {
                            logs.sortedBy { it.dateWorked }.groupBy(date)
                        }
                        is WorkLogGroupBy.Issue -> {
                            logs.sortedBy { it.issueId }.groupBy(issue)
                        }
                    }
                }
                is OrderType.Descending -> {
                    when (groupBy) {
                        is WorkLogGroupBy.Date -> {
                            logs.sortedByDescending { it.dateWorked }.groupBy(date)
                        }
                        is WorkLogGroupBy.Issue -> {
                            logs.sortedByDescending { it.issueId }.groupBy(issue)
                        }
                    }
                }
            }
        }
    }
}