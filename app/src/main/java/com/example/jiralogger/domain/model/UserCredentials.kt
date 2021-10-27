package com.example.jiralogger.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.jiralogger.data.remote.dto.AvatarUrls

@Entity(tableName = "user_credentials", primaryKeys = ["key", "name"])
data class UserCredentials(
    @ColumnInfo(name = "id") val key: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val emailAddress: String,
    @ColumnInfo(name = "avatar_urls") val avatarUrls: AvatarUrls,
    @ColumnInfo(name = "is_logged_in") val loggedIn: Boolean
)