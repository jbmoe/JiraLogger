package com.example.jiralogger.domain.repository

import com.example.jiralogger.domain.model.Log
import kotlinx.coroutines.flow.Flow

interface LogRepository {
    fun getLogs(): Flow<List<Log>>
    suspend fun getLogById(id: Int): Log?
    suspend fun insertLog(log: Log)
    suspend fun deleteLog(log: Log)
}