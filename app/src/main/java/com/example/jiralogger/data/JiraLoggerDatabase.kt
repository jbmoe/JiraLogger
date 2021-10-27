package com.example.jiralogger.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.model.UserCredentials

@Database(
    entities = [WorkLog::class, UserCredentials::class],
    version = 2
)
abstract class JiraLoggerDatabase : RoomDatabase() {
    abstract val jiraLoggerDao: JiraLoggerDao

    companion object {
        const val DATABASE_NAME = "jiralogger_db"
    }
}