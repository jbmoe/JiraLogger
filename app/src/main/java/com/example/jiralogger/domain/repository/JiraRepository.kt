package com.example.jiralogger.domain.repository

import com.example.jiralogger.data.remote.dto.UserCredentials
import com.example.jiralogger.domain.model.Issue

interface JiraRepository {
    suspend fun getIssues(): List<Issue>

    suspend fun getIssuesByFilter(filter: String?): List<Issue>

    suspend fun getIssueByKey(issueKey: String): Issue?

    suspend fun getUserCredentials(username: String): UserCredentials
}