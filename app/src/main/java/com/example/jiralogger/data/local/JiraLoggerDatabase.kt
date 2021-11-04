package com.example.jiralogger.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jiralogger.domain.model.WorkLog

@Database(
    entities = [WorkLog::class],
    version = 3
)
abstract class JiraLoggerDatabase : RoomDatabase() {
    abstract val jiraLoggerDao: JiraLoggerDao

    companion object {
        const val DATABASE_NAME = "jiralogger_db"
    }

}