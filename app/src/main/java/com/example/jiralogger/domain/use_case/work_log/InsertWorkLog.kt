package com.example.jiralogger.domain.use_case.work_log

import com.example.jiralogger.domain.model.InvalidLogException
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.repository.DbRepository
import javax.inject.Inject

class InsertWorkLog @Inject constructor(
    private val repository: DbRepository
) {
    @Throws(InvalidLogException::class)
    suspend operator fun invoke(workLog: WorkLog) {
        if (workLog.timeSpent.isBlank()) {
            throw InvalidLogException("You must specify amount of time")
        }
        repository.insertWorkLog(workLog)
    }
}