package com.example.jiralogger.data.repository

import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.repository.ApiRepository
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.domain.model.UserCredentials

class ApiRepositoryTestImpl : ApiRepository {
    private val data = TestData.API_RESULT_TEST_OBJECT.toIssuesList()

    override suspend fun getIssuesByFilter(filter: String): List<Issue> {
        return data.shuffled()
    }

    override suspend fun getIssuesByFilter(filter: String, ignoreCache: Boolean): List<Issue> {
        return getIssuesByFilter(filter)
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