package com.example.jiralogger.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.presentation.issue_detail.IssueDetailScreen
import com.example.jiralogger.presentation.issue_list.IssueListScreen
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.Screen
import com.example.jiralogger.presentation.work_log_add_edit.AddEditScreen
import com.example.jiralogger.presentation.work_log_list.WorkLogListScreen
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JiraLoggerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
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
                            Screen.WorkLogAddEdit.route +
                                    "?${Constants.PARAM_WORK_LOG_ID}={${Constants.PARAM_WORK_LOG_ID}}" +
                                    "&${Constants.PARAM_ISSUE_KEY}={${Constants.PARAM_ISSUE_KEY}}",
                            arguments = listOf(
                                navArgument(Constants.PARAM_WORK_LOG_ID) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(Constants.PARAM_ISSUE_KEY) {
                                    type = NavType.StringType
                                    nullable = true
                                }
                            )
                        ) {
                            AddEditScreen(navController)
                        }
                    }
                }
            }
        }
    }
}