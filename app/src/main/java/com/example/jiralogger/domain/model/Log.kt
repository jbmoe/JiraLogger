package com.example.jiralogger.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Log(
    @PrimaryKey val id: Int? = null,

)
