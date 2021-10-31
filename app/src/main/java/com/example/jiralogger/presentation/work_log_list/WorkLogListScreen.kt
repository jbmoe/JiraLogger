package com.example.jiralogger.presentation.work_log_list

import android.content.res.Configuration
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.presentation.components.SharedList
import com.example.jiralogger.presentation.components.SharedTopAppBar
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.preview_paramater.WorkLogListPreviewParameterProvider
import com.example.jiralogger.presentation.work_log_list.components.WorkLogListItem

@Composable
fun WorkLogListScreen(
    navController: NavController,
    viewModel: WorkLogListViewModel = hiltViewModel()
) {

}

@Composable
fun Content(
    state: WorkLogListState,
    onItemClicked: (WorkLog) -> Unit = {},
    onEvent: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            SharedTopAppBar(
                title = {
                    Text(text = "Work Logs")
                },
            )
        }
    ) {
        SharedList(state = state) { workLog ->
            WorkLogListItem(
                workLog = workLog as WorkLog,
                onItemClicked = { onItemClicked(workLog) }
            )
        }
    }
}

@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(WorkLogListPreviewParameterProvider::class) state: WorkLogListState) {
    JiraLoggerTheme {
        Content(state = state)
    }
}