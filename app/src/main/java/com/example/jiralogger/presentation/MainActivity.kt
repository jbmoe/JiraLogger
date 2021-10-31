package com.example.jiralogger.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jiralogger.presentation.issue_detail.IssueDetailScreen
import com.example.jiralogger.presentation.issue_list.IssueListScreen
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.Screen
import com.example.jiralogger.presentation.work_log_list.WorkLogListScreen
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JiraLoggerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.IssueListScreen.route
                    ) {
                        composable(Screen.IssueListScreen.route) {
                            IssueListScreen(navController)
                        }
                        composable(Screen.IssueDetailScreen.route + "/{issueKey}") {
                            IssueDetailScreen() {
                                navController.popBackStack()
                            }
                        }
                        composable(Screen.WorkLogListScreen.route) {
                            WorkLogListScreen(navController)
                        }
                    }
                }
            }
        }
    }
}