package com.example.jiralogger.data.remote

import com.example.jiralogger.data.remote.dto.ApiResult
import com.example.jiralogger.data.remote.dto.IssueDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JiraApi {
    @GET("/rest/api/2/search")
    suspend fun getIssues(): ApiResult

    @GET("/rest/api/2/search?jql")
    suspend fun getIssuesByFilter(@Query("jql") filter: String): ApiResult


    @GET("/rest/api/2/issue/{issueKey}")
    suspend fun getIssueByKey(@Path("issueKey") issueKey: String): IssueDto
}