package com.example.jiralogger.domain.repository

import com.example.jiralogger.domain.model.WorkLog
import kotlinx.coroutines.flow.Flow

interface DbRepository {
    fun getLogs(): Flow<List<WorkLog>>
    suspend fun getLogById(id: Int): WorkLog?
    suspend fun insertLog(workLog: WorkLog)
    suspend fun deleteLog(workLog: WorkLog)
}