package com.example.jiralogger.data

import androidx.room.*
import com.example.jiralogger.domain.model.Log
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {
    @Query("SELECT * FROM log")
    fun getLogs(): Flow<List<Log>>

    @Query("SELECT * FROM log WHERE id = :id")
    suspend fun getLogById(id: Int): Log?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: Log)

    @Delete
    suspend fun deleteLog(log: Log)
}