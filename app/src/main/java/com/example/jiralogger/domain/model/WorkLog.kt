package com.example.jiralogger.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "work_log")
data class WorkLog(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo(name = "issue_id") val issueId: String,
    @ColumnInfo(name = "user_id") val user: UserCredentials,
    @ColumnInfo(name = "time_spent") val timeSpent: String,
    @ColumnInfo(name = "time_spent_seconds") val timeSpentSeconds: Int,
    @ColumnInfo(name = "date_worked") val dateWorked: Long,
    @ColumnInfo(name = "comment") val comment: String

)

//data class WorkLog(
//    val id: String,
//    val issueId: String,
//    val author: Author,
//    val comment: String,
//    val created: String,
//    val started: String,
//    val timeSpent: String,
//    val timeSpentSeconds: Int,
//    val updated: String
//)