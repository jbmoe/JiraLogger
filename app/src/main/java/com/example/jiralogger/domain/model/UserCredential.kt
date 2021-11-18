package com.example.jiralogger.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.jiralogger.data.remote.dto.AvatarUrls

@Entity(tableName = "user_credentials", primaryKeys = ["id", "name"])
data class UserCredential(
    @ColumnInfo(name = "id") val key: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "email") val emailAddress: String,
    @ColumnInfo(name = "is_logged_in") val loggedIn: Boolean
)

class InvalidUserCredentialException(message: String) : Exception(message)