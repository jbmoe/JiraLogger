package com.example.jiralogger.data.repository

import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.repository.JiraRepository
import com.example.jiralogger.common.TestData

class TestImpl : JiraRepository {
    private val data = TestData.API_RESULT_TEST_OBJECT

    override suspend fun getIssues(): List<Issue> {
        return data.toIssuesList()
    }

    override suspend fun getIssuesByFilter(filter: String): List<Issue> {
        TODO("Not yet implemented")
    }

    override suspend fun getIssueByKey(issueKey: String): Issue {
        return data.issues.find {
            it.key.equals(issueKey)
        }!!.toIssue()
    }
}