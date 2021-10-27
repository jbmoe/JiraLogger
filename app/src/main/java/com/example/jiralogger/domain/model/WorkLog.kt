package com.example.jiralogger.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "work_log")
data class WorkLog(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "issue_id") val issueId: String,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "time_spent") val timeSpent: String,
    @ColumnInfo(name = "time_spent_seconds") val timeSpentSeconds: Int,
    @ColumnInfo(name = "date_worked") val dateWorked: Long,
    @ColumnInfo(name = "comment") val comment: String
)