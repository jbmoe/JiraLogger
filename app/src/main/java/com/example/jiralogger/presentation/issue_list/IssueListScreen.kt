package com.example.jiralogger.presentation.issue_list

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jiralogger.R
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.util.IssueFilter
import com.example.jiralogger.presentation.components.BottomNavigationBar
import com.example.jiralogger.presentation.components.SharedList
import com.example.jiralogger.presentation.components.SharedScaffold
import com.example.jiralogger.presentation.components.Text
import com.example.jiralogger.presentation.issue_list.components.IssueListItem
import com.example.jiralogger.presentation.issue_list.components.Searchbar
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
        filters = viewModel.filters,
        onItemClicked = { issue ->
            navController.navigate(Screen.IssueDetailScreen.route + "/${issue.key}")
        },
        onEvent = { viewModel.onEvent(it) },
        navController = navController
    )
}

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    state: IssueListState,
    filters: List<IssueFilter>,
    onItemClicked: (Issue) -> Unit = {},
    onEvent: (IssuesEvent) -> Unit = {},
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    SharedScaffold(
        state = scaffoldState,
        title = { Text(text = "Issues") },
        actions = {
            IconButton(onClick = { onEvent(IssuesEvent.ToggleSearchVisibility) }) {
                Icon(Icons.Default.Search, "Search")
            }
            IconButton(onClick = { onEvent(IssuesEvent.Refresh) }) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Refresh"
                )
            }
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        Column {
            val fadein = fadeIn() + scaleIn() + expandVertically()
            val fadeout = fadeOut() + scaleOut() + shrinkVertically()
            AnimatedVisibility(visible = state.searchIsVisible, enter = fadein, exit = fadeout) {
                Searchbar {
                    onEvent(IssuesEvent.Search(IssueFilter.SEARCH(it)))
                }
            }
            AnimatedVisibility(visible = state.filterIsVisible, enter = fadein, exit = fadeout) {
                TabSection(
                    currentTab = state.issueFilter,
                    tabs = filters,
                    onTabChange = { onEvent(IssuesEvent.Filter(it)) })
            }
            SharedList(
                isError = state.error.isNotBlank(),
                errorText = state.error,
                isLoading = state.isLoading
            ) {
                item {
                    Spacer(Modifier.padding(2.dp))
                }
                items(state.items) { issue ->
                    IssueListItem(issue = issue, onItemClicked = {
                        onItemClicked(it)
                    })
                }
                item {
                    Spacer(Modifier.padding(2.dp))
                }

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
        Content(state = state, navController = rememberNavController(), filters = emptyList())
    }
}