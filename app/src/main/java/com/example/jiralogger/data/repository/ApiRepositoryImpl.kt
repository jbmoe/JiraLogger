package com.example.jiralogger.data.repository

import com.example.jiralogger.data.remote.JiraApi
import com.example.jiralogger.domain.model.UserCredentials
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.repository.ApiRepository
import javax.inject.Inject

class
ApiRepositoryImpl @Inject constructor(
    private val api: JiraApi
) : ApiRepository {
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