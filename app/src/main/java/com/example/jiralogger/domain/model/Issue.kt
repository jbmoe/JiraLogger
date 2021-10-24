package com.example.jiralogger.domain.model


data class Issue(
    val id: String?,
    val key: String?,
    val projectName: String?,
    val projectImageUrl: String?,
    val projectImage: Int?,
    val priorityName: String?,
    val priorityUrl: String?,
    val summary: String?,
    val description: String?
)
