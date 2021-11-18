package com.example.jiralogger.data.local

import androidx.room.*
import com.example.jiralogger.domain.model.UserCredential
import com.example.jiralogger.domain.model.WorkLog
import kotlinx.coroutines.flow.Flow

@Dao
interface JiraLoggerDao {
    @Query("SELECT * FROM work_log")
    fun getWorkLogs(): Flow<List<WorkLog>>

    @Query("SELECT * FROM work_log WHERE id = :id")
    suspend fun getWorkLogById(id: Int): WorkLog?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkLog(workLog: WorkLog)

    @Delete
    suspend fun deleteWorkLog(workLog: WorkLog)

    @Query("SELECT * FROM user_credentials WHERE name = :name")
    suspend fun getUserCredential(name: String): UserCredential

    @Query("SELECT * FROM user_credentials WHERE name = :username AND password = :password")
    suspend fun getUserCredential(username: String, password: String): UserCredential

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserCredentials(userCredential: UserCredential)

    @Delete
    suspend fun deleteUserCredentials(userCredential: UserCredential)
}