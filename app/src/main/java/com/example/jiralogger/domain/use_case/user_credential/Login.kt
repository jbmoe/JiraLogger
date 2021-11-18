package com.example.jiralogger.domain.use_case.user_credential

import com.example.jiralogger.domain.repository.DbRepository
import javax.inject.Inject

class Login @Inject constructor(
    private val repository: DbRepository,
    private val insertUserCredential: InsertUserCredential
) {
    suspend operator fun invoke(username: String, password: String): Boolean {
        val uc = repository.getUserCredential(username, password)

        return if (uc != null) {
            insertUserCredential(uc.copy(loggedIn = true))
            true
        } else false
    }
}