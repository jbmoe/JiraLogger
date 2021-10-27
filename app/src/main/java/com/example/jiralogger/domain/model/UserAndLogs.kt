package com.example.jiralogger.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndLogs(
    @Embedded val user: UserCredentials,
    @Relation(parentColumn = "id", entityColumn = "user_id")
    val workLogs: List<WorkLog>
)