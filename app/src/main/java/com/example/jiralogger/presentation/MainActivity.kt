package com.example.jiralogger.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.presentation.issue_detail.IssueDetailScreen
import com.example.jiralogger.presentation.issue_list.IssueListScreen
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.Screen
import com.example.jiralogger.presentation.work_log_detail.WorkLogDetailScreen
import com.example.jiralogger.presentation.work_log_list.WorkLogListScreen
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
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
                        composable(
                            Screen.IssueDetailScreen.route + "/{${Constants.PARAM_ISSUE_KEY}}",
                            arguments = listOf(
                                navArgument(Constants.PARAM_ISSUE_KEY) { type = NavType.StringType }
                            )
                        ) {
                            IssueDetailScreen(navController)
                        }
                        composable(Screen.WorkLogListScreen.route) {
                            WorkLogListScreen(navController)
                        }
                        composable(
                            Screen.WorkLogDetail.route +
                                    "?${Constants.PARAM_WORK_LOG_ID}={${Constants.PARAM_WORK_LOG_ID}}" +
                                    "&${Constants.PARAM_IS_EDITING}={${Constants.PARAM_IS_EDITING}}" +
                                    "&${Constants.PARAM_ISSUE_KEY}={${Constants.PARAM_ISSUE_KEY}}",
                            arguments = listOf(
                                navArgument(Constants.PARAM_WORK_LOG_ID) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(Constants.PARAM_IS_EDITING) {
                                    type = NavType.BoolType
                                    defaultValue = false
                                },
                                navArgument(Constants.PARAM_ISSUE_KEY) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )
                        ) {
                            WorkLogDetailScreen(navController)
                        }
                    }
                }
            }
        }
    }
}