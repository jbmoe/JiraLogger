package com.example.jiralogger.data.repository

import androidx.compose.foundation.lazy.rememberLazyListState
import com.example.jiralogger.data.LogDao
import com.example.jiralogger.domain.model.Log
import com.example.jiralogger.domain.repository.LogRepository
import kotlinx.coroutines.flow.Flow

class LogRepositoryImpl(
    private val dao: LogDao
) : LogRepository {
    override fun getLogs(): Flow<List<Log>> {
        return dao.getLogs()
    }

    override suspend fun getLogById(id: Int): Log? {
        return dao.getLogById(id)
    }

    override suspend fun insertLog(log: Log) {
        return dao.insertLog(log)
    }

    override suspend fun deleteLog(log: Log) {
        return dao.deleteLog(log)
    }
}