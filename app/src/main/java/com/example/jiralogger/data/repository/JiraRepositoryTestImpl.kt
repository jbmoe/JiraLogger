package com.example.jiralogger.data.repository

import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.repository.JiraRepository
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.data.remote.dto.UserCredentials

class JiraRepositoryTestImpl : JiraRepository {
    private val data = TestData.API_RESULT_TEST_OBJECT.toIssuesList()

    override suspend fun getIssuesByFilter(filter: String?): List<Issue> {
        return data.shuffled()
    }

    override suspend fun getIssueByKey(issueKey: String): Issue? {
        return data.find {
            it.key == issueKey
        }
    }

    override suspend fun getUserCredentials(username: String): UserCredentials {
        TODO("Not yet implemented")
    }
}