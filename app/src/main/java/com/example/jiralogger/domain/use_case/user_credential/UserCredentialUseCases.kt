package com.example.jiralogger.domain.use_case.user_credential

data class UserCredentialUseCases(
    val getUserCredential: GetUserCredential,
    val insertUserCredential: InsertUserCredential,
    val deleteUserCredential: DeleteUserCredential
)
