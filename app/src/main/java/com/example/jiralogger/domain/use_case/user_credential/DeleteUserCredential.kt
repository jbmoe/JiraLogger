package com.example.jiralogger.domain.use_case.user_credential

import com.example.jiralogger.domain.model.UserCredential
import com.example.jiralogger.domain.repository.DbRepository
import javax.inject.Inject

class DeleteUserCredential @Inject constructor(
    private val repository: DbRepository
) {
    suspend operator fun invoke(userCredential: UserCredential) {
        repository.deleteUserCredentials(userCredential)
    }
}