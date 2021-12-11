package com.example.jiralogger.data.repository

import com.example.jiralogger.common.constant.Filters
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.repository.ApiRepository
import com.example.jiralogger.domain.util.IssueFilter

class ApiRepositoryTestImpl : ApiRepository {
    private val data = TestData.API_RESULT_TEST_OBJECT.toIssuesList()

    override suspend fun getIssuesByFilter(filter: String): List<Issue> {
        return when (filter) {
            Filters.ASSIGNED_TO_ME -> data.subList(0, 13)
            Filters.LAST_SEEN -> data.subList(13, 63)
            Filters.WATCHING -> data.subList(63, 80)
            else -> data.subList(80, data.size)
        }
    }

    override suspend fun getIssuesByFilter(filter: String, ignoreCache: Boolean): List<Issue> {
        return getIssuesByFilter(filter)
    }

    override suspend fun getIssueByKey(issueKey: String): Issue? {
        return data.find {
            it.key == issueKey
        }
    }
}