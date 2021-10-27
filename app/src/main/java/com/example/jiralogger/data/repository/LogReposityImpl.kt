package com.example.jiralogger.data.repository

import com.example.jiralogger.data.JiraLoggerDao
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.repository.DbRepository
import kotlinx.coroutines.flow.Flow

class DbRepositoryImpl(
    private val dao: JiraLoggerDao
) : DbRepository {
    override fun getWorkLogs(): Flow<List<WorkLog>> {
        return dao.getLogs()
    }

    override suspend fun getWorkLogById(id: Int): WorkLog? {
        return dao.getLogById(id)
    }

    override suspend fun insertWorkLog(workLog: WorkLog) {
        return dao.insertLog(workLog)
    }

    override suspend fun deleteWorkLog(workLog: WorkLog) {
        return dao.deleteLog(workLog)
    }
}