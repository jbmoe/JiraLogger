package com.example.jiralogger.presentation.work_log_list

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.presentation.components.BottomNavigationBar
import com.example.jiralogger.presentation.components.SharedList
import com.example.jiralogger.presentation.components.SharedScaffold
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.Screen
import com.example.jiralogger.presentation.util.convertLongToTime
import com.example.jiralogger.presentation.util.preview_paramater.WorkLogListPreviewParameterProvider
import com.example.jiralogger.presentation.work_log_list.components.WorkLogListItem

@ExperimentalFoundationApi
@Composable
fun WorkLogListScreen(
    navController: NavController,
    viewModel: WorkLogListViewModel = hiltViewModel()
) {
    Content(
        state = viewModel.state.value,
        onItemClicked = {
            navController.navigate(Screen.WorkLogDetail.route + "?${Constants.PARAM_WORK_LOG_ID}=${it.id}")
        },
        onEvent = { viewModel.onEvent(it) },
        navController = navController
    )
}

@ExperimentalFoundationApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Content(
    state: WorkLogListState,
    onItemClicked: (WorkLog) -> Unit = {},
    onEvent: (WorkLogsEvent) -> Unit = {},
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
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
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    Screen.IssueListScreen,
                    Screen.WorkLogListScreen
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        SharedList(state = state) {
            state.itemMap.forEach { (date, logs) ->
                item(date) {
                    Text(
                        text = convertLongToTime(date as Long, "E d. MMMM yy"),
                        modifier = Modifier.padding(4.dp, top = 12.dp),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                items(items = logs) { workLog ->
                    WorkLogListItem(
                        workLog = workLog as WorkLog,
                        onItemClicked = { onItemClicked(workLog) }
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(WorkLogListPreviewParameterProvider::class) state: WorkLogListState) {
    JiraLoggerTheme {
        Content(state = state, navController = rememberNavController())
    }
}