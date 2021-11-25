package com.example.jiralogger.domain.use_case.work_log

import com.example.jiralogger.domain.model.InvalidLogException
import com.example.jiralogger.domain.model.LogError
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.repository.DbRepository
import javax.inject.Inject

class InsertWorkLog @Inject constructor(
    private val repository: DbRepository
) {
    @Throws(InvalidLogException::class)
    suspend operator fun invoke(workLog: WorkLog) {
        if (workLog.issueId.isBlank()) throw InvalidLogException(LogError.IssueID)
        if (workLog.dateWorked == 0L) throw InvalidLogException(LogError.Date)
        if (workLog.timeSpent.isBlank()) throw InvalidLogException(LogError.TimeSpent)
        repository.insertWorkLog(workLog)
    }
}