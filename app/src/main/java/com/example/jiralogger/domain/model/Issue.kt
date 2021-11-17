package com.example.jiralogger.domain.model

import com.example.jiralogger.data.remote.dto.Issuetype
import com.example.jiralogger.data.remote.dto.Priority
import com.example.jiralogger.data.remote.dto.Project
import com.example.jiralogger.data.remote.dto.Status

data class Issue(
    val id: String,
    val key: String,
    val project: Project,
    val priority: Priority,
    val status: Status,
    val issuetype: Issuetype,
    val summary: String,
    val description: String
)
