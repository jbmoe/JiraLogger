package com.example.jiralogger.data.repository

import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.repository.JiraRepository
import com.example.jiralogger.common.constant.TestData

class JiraRepositoryTestImpl : JiraRepository {
    private val data = TestData.API_RESULT_TEST_OBJECT.toIssuesList()

    override suspend fun getIssues(): List<Issue> {
        return data
    }

    override suspend fun getIssuesByFilter(filter: String): List<Issue> {
        TODO("Not yet implemented")
    }

    override suspend fun getIssueByKey(issueKey: String): Issue {
        return data.find {
            it.key.equals(issueKey)
        }!!
    }
}