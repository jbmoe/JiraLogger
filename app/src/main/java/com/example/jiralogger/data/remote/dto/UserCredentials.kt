package com.example.jiralogger.data.remote.dto
import com.google.gson.annotations.SerializedName


data class UserCredentials(
    val key: String, // JIRAUSER25235
    val name: String, // jem
    val emailAddress: String, // jem@elbek-vejrup.dk
    val avatarUrls: AvatarUrls,
    val displayName: String // Jeppe Bach Møller
)