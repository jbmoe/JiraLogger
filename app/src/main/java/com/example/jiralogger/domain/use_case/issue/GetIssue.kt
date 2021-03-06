package com.example.jiralogger.domain.use_case.issue

import com.example.jiralogger.common.Resource
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetIssue @Inject constructor(
    private val repository: ApiRepository
) {
    operator fun invoke(issueKey: String): Flow<Resource<Issue?>> = flow {
        try {
            emit(Resource.Loading())
            val issue = repository.getIssueByKey(issueKey)
            emit(Resource.Success(issue))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}