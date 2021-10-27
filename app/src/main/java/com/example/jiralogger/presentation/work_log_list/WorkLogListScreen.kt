package com.example.jiralogger.presentation.work_log_list

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.preview_paramater.WorkLogListPreviewParameterProvider

@Composable
fun WorkLogListScreen(
    navController: NavController,
    viewModel: WorkLogListViewModel = hiltViewModel()
) {

}

@Composable
fun Content(
    state: WorkLogListState
) {

}

@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(WorkLogListPreviewParameterProvider::class) state: WorkLogListState) {
    JiraLoggerTheme {
        Content(state = state)
    }
}