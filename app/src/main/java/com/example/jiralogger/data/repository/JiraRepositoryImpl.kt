package com.example.jiralogger.data.repository

import com.example.jiralogger.data.remote.JiraApi
import com.example.jiralogger.data.remote.dto.UserCredentials
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.repository.JiraRepository
import javax.inject.Inject

class
JiraRepositoryImpl @Inject constructor(
    private val api: JiraApi
) : JiraRepository {
    override suspend fun getIssues(): List<Issue> {
        return api.getIssues().toIssuesList()
    }

    override suspend fun getIssuesByFilter(filter: String?): List<Issue> {
        return api.getIssuesByFilter(filter).toIssuesList()
    }

    override suspend fun getIssueByKey(issueKey: String): Issue {
        return api.getIssueByKey(issueKey).toIssue()
    }

    override suspend fun getUserCredentials(username: String): UserCredentials {
        return api.getUserCredentials(username)
    }
}