package com.example.jiralogger.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val darkScheme = darkColorScheme()
private val lightScheme = lightColorScheme()

@Composable
fun JiraLoggerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val scheme = if (darkTheme) {
        darkScheme
    } else {
        lightScheme
    }

    MaterialTheme(
        colorScheme = scheme,
        content = content
    )
}