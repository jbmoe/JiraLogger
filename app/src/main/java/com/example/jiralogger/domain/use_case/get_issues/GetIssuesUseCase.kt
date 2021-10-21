package com.example.jiralogger.domain.use_case.get_issues

import com.example.jiralogger.common.Resource
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.repository.JiraRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetIssuesUseCase @Inject constructor(
    private val repository: JiraRepository
) {
    operator fun invoke(): Flow<Resource<List<Issue>>> = flow {
        try {
            emit(Resource.Loading())
            val issues = repository.getIssues()
            emit(Resource.Success(issues))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpeted error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}