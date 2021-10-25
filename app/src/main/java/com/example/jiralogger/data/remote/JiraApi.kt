package com.example.jiralogger.data.remote

import com.example.jiralogger.data.remote.dto.ApiResponse
import com.example.jiralogger.data.remote.dto.IssueDto
import com.example.jiralogger.data.remote.dto.UserCredentials
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface JiraApi {
    @GET("rest/api/2/search")
    suspend fun getIssues(): ApiResponse

    @GET("rest/api/2/search")
    suspend fun getIssuesByFilter(@Query(value = "jql") filter: String? = null): ApiResponse

    @GET("rest/api/2/issue/{issueKey}")
    suspend fun getIssueByKey(@Path("issueKey") issueKey: String): IssueDto

    @GET("rest/api/2/user")
    suspend fun getUserCredentials(@Query("username") username: String): UserCredentials
}