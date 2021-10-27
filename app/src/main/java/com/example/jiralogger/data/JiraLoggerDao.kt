package com.example.jiralogger.data

import androidx.room.*
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
}