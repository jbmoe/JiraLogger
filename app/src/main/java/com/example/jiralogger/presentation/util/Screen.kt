package com.example.jiralogger.presentation.util

sealed class Screen(val route: String) {
    object IssueListScreen: Screen("issue_list_screen")
    object IssueDetailScreen: Screen("issue_detail_screen")
}