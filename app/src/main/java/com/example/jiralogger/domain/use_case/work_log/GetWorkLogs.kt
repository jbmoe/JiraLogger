package com.example.jiralogger.domain.use_case.work_log

import com.example.jiralogger.common.Resource
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.repository.DbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetWorkLogs @Inject constructor(
    private val repository: DbRepository
) {
    operator fun invoke(): Flow<Resource<List<WorkLog>>> = flow {
        try {
            emit(Resource.Loading())
            val workLogs = repository.getWorkLogs()
            emit(Resource.Success(workLogs))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}