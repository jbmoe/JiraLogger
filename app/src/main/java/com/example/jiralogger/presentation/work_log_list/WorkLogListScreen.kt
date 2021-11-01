package com.example.jiralogger.presentation.work_log_list

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.presentation.components.BottomNavigationBar
import com.example.jiralogger.presentation.components.SharedList
import com.example.jiralogger.presentation.components.SharedScaffold
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.Screen
import com.example.jiralogger.presentation.util.preview_paramater.WorkLogListPreviewParameterProvider
import com.example.jiralogger.presentation.work_log_list.components.WorkLogListItem

@Composable
fun WorkLogListScreen(
    navController: NavController,
    viewModel: WorkLogListViewModel = hiltViewModel()
) {
    Content(
        state = viewModel.state.value,
        onItemClicked = {
            navController.navigate(Screen.WorkLogDetail.route + "/${it.id}")
        },
        onEvent = { viewModel.onEvent(it) },
        navController = navController
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Content(
    state: WorkLogListState,
    onItemClicked: (WorkLog) -> Unit = {},
    onEvent: (WorkLogsEvent) -> Unit = {},
    navController: NavController
) {
    SharedScaffold(
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
        SharedList(modifier = Modifier.padding(horizontal = 8.dp), state = state) { workLog ->
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
            WorkLogListItem(
                workLog = workLog as WorkLog,
                onItemClicked = { onItemClicked(workLog) }
            )
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
        }
    }
}

@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(WorkLogListPreviewParameterProvider::class) state: WorkLogListState) {
    JiraLoggerTheme {
        Content(state = state, navController = rememberNavController())
    }
}