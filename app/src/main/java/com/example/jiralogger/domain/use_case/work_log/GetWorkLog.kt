package com.example.jiralogger.domain.use_case.work_log

import com.example.jiralogger.common.Resource
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.repository.DbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetWorkLog @Inject constructor(
    private val repository: DbRepository
) {
    suspend operator fun invoke(id: Int): Flow<Resource<WorkLog?>> = flow {
        try {
            emit(Resource.Loading())
            val workLog = repository.getWorkLogById(id)
            emit(Resource.Success(workLog))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}