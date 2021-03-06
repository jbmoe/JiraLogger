package com.example.jiralogger.domain.repository

import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.model.UserCredentials

interface ApiRepository {
    suspend fun getIssuesByFilter(filter: String): List<Issue>

    suspend fun getIssuesByFilter(filter: String, ignoreCache: Boolean): List<Issue>

    suspend fun getIssueByKey(issueKey: String): Issue?

    suspend fun getUserCredentials(username: String): UserCredentials
}