package com.example.jiralogger.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme

@Composable
fun SharedTopAppBar(
    title: @Composable () -> Unit,
    actions: List<@Composable () -> Unit>? = null,
    navigationIcon: @Composable (() -> Unit)? = null
) {
    SmallTopAppBar(
        title = {
            title()
        },
        actions = {
            actions?.forEach {
                it()
            }
        },
        navigationIcon = {
            navigationIcon?.invoke()
        }
    )
}

@Composable
@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun Preview1() {
    JiraLoggerTheme {
        SharedTopAppBar(
            title = { Text("Title") },
            actions = listOf {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Refresh")
                }
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
        )
    }
}