package com.example.jiralogger.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jiralogger.domain.model.Log

@Database(
    entities = [Log::class],
    version = 1
)
abstract class LogDatabase : RoomDatabase() {
    abstract val logDao: LogDao

    companion object {
        const val DATABASE_NAME = "logs_db"
    }
}