package com.example.jiralogger.presentation.issue_list

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jiralogger.R
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.presentation.components.BottomNavigationBar
import com.example.jiralogger.presentation.components.SharedList
import com.example.jiralogger.presentation.components.SharedScaffold
import com.example.jiralogger.presentation.issue_list.components.IssueListItem
import com.example.jiralogger.presentation.issue_list.components.TabSection
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.Screen
import com.example.jiralogger.presentation.util.preview_paramater.IssueListPreviewParameterProvider

@OptIn(ExperimentalAnimationApi::class)
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
        onEvent = { issueEvent ->
            viewModel.onEvent(issueEvent)
        },
        navController = navController
    )
}

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    state: IssueListState,
    onItemClicked: (Issue) -> Unit = {},
    onEvent: (IssuesEvent) -> Unit = {},
    navController: NavController
) {
    SharedScaffold(
        title = { Text(text = "Issues") },
        actions = {
            IconButton(onClick = { onEvent(IssuesEvent.Refresh) }) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Refresh"
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_filter_alt_24),
                    contentDescription = "Filter"
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
                    if (navController.currentBackStackEntry?.destination?.route != it.route)
                        navController.navigate(it.route)
                }
            )
        }
    ) {
        Column {
            TabSection(onFilterChange = { onEvent(IssuesEvent.Filter(it)) })
            SharedList(state = state) { issue ->
                IssueListItem(
                    issue = issue as Issue,
                    onItemClicked = { onItemClicked(issue) }
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(name = "Light mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(IssueListPreviewParameterProvider::class) state: IssueListState) {
    JiraLoggerTheme {
        Content(state = state, navController = rememberNavController())
    }
}

