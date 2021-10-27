package com.example.jiralogger.domain.use_case.get_user_credentials

import com.example.jiralogger.common.Resource
import com.example.jiralogger.domain.model.UserCredentials
import com.example.jiralogger.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserCredentials @Inject constructor(
    private val repository: ApiRepository
) {
    operator fun invoke(username: String): Flow<Resource<UserCredentials>> = flow {
        try {
            emit(Resource.Loading())
            val userCredentials = repository.getUserCredentials(username)
            emit(Resource.Success(userCredentials))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}