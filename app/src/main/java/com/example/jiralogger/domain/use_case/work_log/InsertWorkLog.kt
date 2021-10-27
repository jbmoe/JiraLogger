package com.example.jiralogger.domain.use_case.work_log

import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.repository.DbRepository
import javax.inject.Inject

class InsertWorkLog @Inject constructor(
    private val repository: DbRepository
) {
    suspend operator fun invoke(workLog: WorkLog) {
        repository.insertWorkLog(workLog)
    }
}