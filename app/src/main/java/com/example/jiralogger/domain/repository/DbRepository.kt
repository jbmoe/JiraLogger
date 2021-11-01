package com.example.jiralogger.domain.repository

import com.example.jiralogger.domain.model.WorkLog
import kotlinx.coroutines.flow.Flow

interface DbRepository {
    fun getWorkLogs(): List<WorkLog>
    suspend fun getWorkLogById(id: Int): WorkLog?
    suspend fun insertWorkLog(workLog: WorkLog)
    suspend fun deleteWorkLog(workLog: WorkLog)
}