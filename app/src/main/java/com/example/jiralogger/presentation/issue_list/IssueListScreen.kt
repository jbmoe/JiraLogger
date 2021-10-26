package com.example.jiralogger.presentation.issue_list

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.domain.util.IssueFilter
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
            TopBar(
                onRefresh = { onEvent(IssuesEvent.Refresh) },
                onFilterChange = { onEvent(IssuesEvent.Filter(it)) }
            )
        }) {
        List(
            state = state,
            onItemClicked = { issue ->
                onItemClicked(issue)
            }
        )
    }
}

@Composable
private fun TopBar(
    onRefresh: () -> Unit,
    onFilterChange: (IssueFilter) -> Unit
) {
    Column {
        TopAppBar(
            title = {
                Text(text = "Issues")
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                }
                IconButton(onClick = onRefresh) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "More")
                }
            },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
        )
        TabSection(onFilterChange = {
            onFilterChange(it)
        })
    }
}

@Composable
private fun List(
    state: IssueListState,
    onItemClicked: (Issue) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.issues) { issue ->
                IssueListItem(
                    issue = issue!!,
                    onItemClicked = { onItemClicked(issue) }
                )
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )
            }
        }
        if (state.error.isNotBlank()) {
            ErrorText(state, Modifier.align(Alignment.Center))
        }
        if (state.isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}

@Composable
private fun ErrorText(state: IssueListState, modifier: Modifier = Modifier) {
    Text(
        text = state.error,
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    )
}

@Preview(name = "Light mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(IssueListPreviewParameterProvider::class) state: IssueListState) {
    JiraLoggerTheme {
        Content(state = state)
    }
}

