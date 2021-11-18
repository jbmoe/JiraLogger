package com.example.jiralogger.domain.use_case.user_credential

import com.example.jiralogger.domain.model.InvalidUserCredentialException
import com.example.jiralogger.domain.model.UserCredential
import com.example.jiralogger.domain.repository.DbRepository
import javax.inject.Inject

class InsertUserCredential @Inject constructor(
    private val repository: DbRepository
) {
    @Throws(InvalidUserCredentialException::class)
    suspend operator fun invoke(userCredential: UserCredential) {
        if (userCredential.name.isBlank()) {
            throw InvalidUserCredentialException("You must specify name")
        }
        if (userCredential.password.isBlank()) {
            throw InvalidUserCredentialException("You must specify password")
        }
        repository.insertUserCredentials(userCredential)
    }
}