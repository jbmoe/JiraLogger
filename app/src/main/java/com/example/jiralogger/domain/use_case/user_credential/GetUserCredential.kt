package com.example.jiralogger.domain.use_case.user_credential

import com.example.jiralogger.domain.model.UserCredential
import com.example.jiralogger.domain.repository.DbRepository
import javax.inject.Inject

class GetUserCredential @Inject constructor(
    private val repository: DbRepository
) {
    suspend operator fun invoke(name: String): UserCredential? {
        return repository.getUserCredential(name)
    }
}