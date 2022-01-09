package com.example.jiralogger.presentation.work_log_list

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jiralogger.R
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.domain.util.WorkLogGroupBy
import com.example.jiralogger.presentation.components.BottomNavigationBar
import com.example.jiralogger.presentation.components.SharedList
import com.example.jiralogger.presentation.components.SharedScaffold
import com.example.jiralogger.presentation.components.Text
import com.example.jiralogger.presentation.issue_list.components.TabSection
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.Screen
import com.example.jiralogger.presentation.util.preview_paramater.WorkLogListPreviewParameterProvider
import com.example.jiralogger.presentation.work_log_list.components.WorkLogListItem
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun WorkLogListScreen(
    navController: NavController,
    viewModel: WorkLogListViewModel = hiltViewModel()
) {
    Content(
        state = viewModel.state.value,
        groupBys = viewModel.groupBys,
        onItemClicked = {
            navController.navigate(Screen.WorkLogAddEdit.route + "?${Constants.PARAM_WORK_LOG_ID}=${it.id}")
        },
        onEvent = { viewModel.onEvent(it) },
        navController = navController
    )
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun Content(
    state: WorkLogListState,
    groupBys: List<WorkLogGroupBy>,
    onItemClicked: (WorkLog) -> Unit = {},
    onEvent: (WorkLogsEvent) -> Unit = {},
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    SharedScaffold(
        state = scaffoldState,
        title = { Text("Work Logs") },
        actions = {
            IconButton(onClick = { onEvent(WorkLogsEvent.Refresh) }) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Refresh"
                )
            }
        },
        FAB = {
            FloatingActionButton(onClick = { navController.navigate(Screen.WorkLogAddEdit.route) }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_more_time_24), "")
            }
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        Column {
            AnimatedVisibility(visible = state.groupByIsVisible) {
                TabSection(
                    currentTab = state.groupBy,
                    tabs = groupBys,
                    onTabChange = { onEvent(WorkLogsEvent.GroupBy(it)) }
                )
            }
            SharedList {
                state.itemMap.forEach { (date, logs) ->
                    item(date) {
                        Text(
                            text = date,
                            modifier = Modifier.padding(2.dp, top = 8.dp)
                        )
                    }
                    items(items = logs) { workLog ->
                        WorkLogListItem(
                            workLog = workLog,
                            onItemClicked = { onItemClicked(workLog) },
                            onDelete = {
                                onEvent(WorkLogsEvent.DeleteLog(workLog))
                                scope.launch {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Log deleted",
                                        actionLabel = "Undo"
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        onEvent(WorkLogsEvent.RestoreLog)
                                    }
                                }
                            }
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(WorkLogListPreviewParameterProvider::class) state: WorkLogListState) {
    JiraLoggerTheme {
        Content(state = state, navController = rememberNavController(), groupBys = emptyList())
    }
}