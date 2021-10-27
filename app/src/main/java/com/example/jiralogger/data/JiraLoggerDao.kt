package com.example.jiralogger.data

import androidx.room.*
import com.example.jiralogger.domain.model.WorkLog
import kotlinx.coroutines.flow.Flow

@Dao
interface JiraLoggerDao {
    @Query("SELECT * FROM work_log")
    fun getLogs(): Flow<List<WorkLog>>

    @Query("SELECT * FROM work_log WHERE id = :id")
    suspend fun getLogById(id: Int): WorkLog?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(workLog: WorkLog)

    @Delete
    suspend fun deleteLog(workLog: WorkLog)
}