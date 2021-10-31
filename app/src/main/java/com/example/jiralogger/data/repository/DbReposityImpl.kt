package com.example.jiralogger.data.repository

import com.example.jiralogger.data.local.JiraLoggerDao
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.repository.DbRepository
import kotlinx.coroutines.flow.Flow

class DbRepositoryImpl(
    private val dao: JiraLoggerDao
) : DbRepository {
    override fun getWorkLogs(): Flow<List<WorkLog>> {
        return dao.getWorkLogs()
    }

    override suspend fun getWorkLogById(id: Int): WorkLog? {
        return dao.getWorkLogById(id)
    }

    override suspend fun insertWorkLog(workLog: WorkLog) {
        return dao.insertWorkLog(workLog)
    }

    override suspend fun deleteWorkLog(workLog: WorkLog) {
        return dao.deleteWorkLog(workLog)
    }
}