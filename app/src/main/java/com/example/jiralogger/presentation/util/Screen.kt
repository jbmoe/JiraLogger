package com.example.jiralogger.presentation.util

import com.example.jiralogger.R

sealed class Screen(
    val name: String? = null,
    val drawableId: Int? = null,
    val route: String
) {
    object LoginScreen : Screen(route = "login")
    object IssueListScreen :
        Screen(
            name = "Issues",
            drawableId = R.drawable.ic_baseline_assignment_24,
            route = "issue_list_screen"
        )
    object IssueDetailScreen : Screen(route = "issue_detail_screen")
    object WorkLogListScreen : Screen(
        name = "Work Logs",
        drawableId = R.drawable.ic_baseline_access_time_filled_24,
        route = "work_log_list_screen"
    )
    object WorkLogDetail : Screen(route = "work_log_detail_screen")
}