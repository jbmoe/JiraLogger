package com.example.jiralogger.domain.model


data class Issue(
    val id: String?,
    val key: String?,
    val projectName: String?,
    val projectImageUrl: String?,
    var projectImage: Int?,
    val summary: String?,
    val description: String?
)
