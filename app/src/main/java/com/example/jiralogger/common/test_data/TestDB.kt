package com.example.jiralogger.common.test_data

import com.example.jiralogger.data.local.JiraLoggerDao
import com.example.jiralogger.domain.model.WorkLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object TestDB : JiraLoggerDao {
    private var _workLogs: MutableList<WorkLog> = mutableListOf()

    override fun getWorkLogs(): Flow<List<WorkLog>> = flow {
        emit(_workLogs)
    }

    override suspend fun getWorkLogById(id: Int): WorkLog? {
        return _workLogs.find { it.id == id }
    }

    override suspend fun insertWorkLog(workLog: WorkLog) {
        _workLogs.add(workLog)
    }

    override suspend fun deleteWorkLog(workLog: WorkLog) {
        _workLogs.remove(workLog)
    }
}