package com.example.jiralogger.data.repository

import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.repository.DbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DbRepositoryTestImpl : DbRepository {

    override fun getWorkLogs(): List<WorkLog> {
        return TestData.WORK_LOG_TEST_DATA
    }

    override suspend fun getWorkLogById(id: Int): WorkLog? {
        return TestData.WORK_LOG_TEST_DATA.find { it.id == id }
    }

    override suspend fun insertWorkLog(workLog: WorkLog) {
        TestData.WORK_LOG_TEST_DATA.add(workLog)
    }

    override suspend fun deleteWorkLog(workLog: WorkLog) {
        TestData.WORK_LOG_TEST_DATA.remove(workLog)
    }
}