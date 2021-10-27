package com.example.jiralogger.data.repository

import com.example.jiralogger.data.JiraLoggerDao
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.repository.DbRepository
import kotlinx.coroutines.flow.Flow

class DbRepositoryImpl(
    private val dao: JiraLoggerDao
) : DbRepository {
    override fun getLogs(): Flow<List<WorkLog>> {
        return dao.getLogs()
    }

    override suspend fun getLogById(id: Int): WorkLog? {
        return dao.getLogById(id)
    }

    override suspend fun insertLog(workLog: WorkLog) {
        return dao.insertLog(workLog)
    }

    override suspend fun deleteLog(workLog: WorkLog) {
        return dao.deleteLog(workLog)
    }
}