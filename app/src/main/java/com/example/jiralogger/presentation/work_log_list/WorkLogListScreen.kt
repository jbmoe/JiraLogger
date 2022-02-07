package com.example.jiralogger.presentation.work_log_list

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
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
import com.example.jiralogger.presentation.components.*
import com.example.jiralogger.presentation.issues.components.TabSection
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.Screen
import com.example.jiralogger.presentation.util.getFormattedTime
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
    Log.d("DEBUGSS", "VM1")
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

@OptIn(ExperimentalMaterial3Api::class)
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
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    CommonScaffold(
        titleText = "Work Logs",
        navigationAction = CommonScaffoldNavigationActions.Menu,
        actions = {
            IconButton(onClick = { onEvent(WorkLogsEvent.Refresh) }) {
                IconPablo(imageVector = Icons.Filled.Refresh)
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.WorkLogAddEdit.route) }) {
                IconPablo(R.drawable.ic_baseline_more_time_24)
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        navController = navController
    ) {
        Column {
            TabSection(
                currentTab = state.groupBy,
                tabs = groupBys,
                onTabChange = { onEvent(WorkLogsEvent.GroupBy(it)) }
            )
            Box(modifier = Modifier.fillMaxSize()) {
                CommonLazyList {
                    state.itemMap.forEach { (key, logs) ->
                        item(key) {
                            Row(
                                horizontalArrangement = SpaceBetween,
                                verticalAlignment = CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 2.dp, end = 2.dp, top = 8.dp)
                            ) {
                                PabloText(text = key)
                                state.totalMap[key]?.let { getFormattedTime(it) }
                                    ?.let { PabloText(text = it) }
                            }
                        }
                        items(items = logs) { workLog ->
                            WorkLogListItem(
                                workLog = workLog,
                                onItemClicked = { onItemClicked(workLog) },
                                onDelete = {
                                    onEvent(WorkLogsEvent.DeleteLog(workLog))
                                    scope.launch {
                                        val result = snackbarHostState.showSnackbar(
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