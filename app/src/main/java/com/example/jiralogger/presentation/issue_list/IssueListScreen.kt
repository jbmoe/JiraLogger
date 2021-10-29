package com.example.jiralogger.presentation.issue_list

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.util.IssueFilter
import com.example.jiralogger.presentation.components.SharedList
import com.example.jiralogger.presentation.components.SharedTopAppBar
import com.example.jiralogger.presentation.issue_list.components.IssueListItem
import com.example.jiralogger.presentation.issue_list.components.TabSection
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.Screen
import com.example.jiralogger.presentation.util.preview_paramater.IssueListPreviewParameterProvider

@Composable
fun IssueListScreen(
    navController: NavController,
    viewModel: IssueListViewModel = hiltViewModel()
) {
    Content(
        state = viewModel.state.value,
        onItemClicked = { issue ->
            navController.navigate(Screen.IssueDetailScreen.route + "/${issue.key}")
        },
        onEvent = { viewModel.onEvent(it) },
    )
}

@Composable
private fun Content(
    state: IssueListState,
    onItemClicked: (Issue) -> Unit = {},
    onEvent: (IssuesEvent) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopPart(
                title = { Text(text = "Issues") },
                onRefresh = { onEvent(IssuesEvent.Refresh) },
                onFilterChange = { onEvent(IssuesEvent.Filter(it)) }
            )
        }) {
        SharedList(state = state) { issue ->
            IssueListItem(
                issue = issue as Issue,
                onItemClicked = { onItemClicked(issue) }
            )
        }
    }
}

@Composable
private fun TopPart(
    title: @Composable () -> Unit,
    onRefresh: () -> Unit,
    onFilterChange: (IssueFilter) -> Unit
) {
    Column {
        SharedTopAppBar(title = title, onRefresh = { onRefresh() })
        TabSection(onFilterChange = { onFilterChange(it) })
    }
}

@Preview(name = "Light mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(IssueListPreviewParameterProvider::class) state: IssueListState) {
    JiraLoggerTheme {
        Content(state = state)
    }
}

