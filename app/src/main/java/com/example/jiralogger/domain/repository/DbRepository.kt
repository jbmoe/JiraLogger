package com.example.jiralogger.domain.repository

import com.example.jiralogger.domain.model.UserCredential
import com.example.jiralogger.domain.model.WorkLog
import kotlinx.coroutines.flow.Flow

interface DbRepository {
    fun getWorkLogs(): Flow<List<WorkLog>>
    suspend fun getWorkLogById(id: Int): WorkLog?
    suspend fun insertWorkLog(workLog: WorkLog)
    suspend fun deleteWorkLog(workLog: WorkLog)

    suspend fun getUserCredential(name: String): UserCredential?
    suspend fun insertUserCredentials(userCredential: UserCredential)
    suspend fun deleteUserCredentials(userCredential: UserCredential)
    suspend fun getUserCredential(username: String, password: String): UserCredential?
}