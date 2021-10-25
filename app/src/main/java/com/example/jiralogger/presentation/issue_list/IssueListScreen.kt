package com.example.jiralogger.presentation.issue_list

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.presentation.util.Screen
import com.example.jiralogger.presentation.issue_list.components.IssueListItem
import com.example.jiralogger.presentation.issue_list.components.Tabs
import com.example.jiralogger.presentation.util.preview_paramater.IssueListPreviewParameterProvider
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme

@Composable
fun IssueListScreen(
    navController: NavController,
    viewModel: IssueListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    ListBody(
        state = state,
        onItemClicked = { issue ->
            navController.navigate(Screen.IssueDetailScreen.route + "/${issue.key}")
        },
        onRefresh = { viewModel.refresh() },
        onLastSeenTapped = { viewModel.getLastSeen() },
        onAssignedToMeTapped = { viewModel.getAssignedToMe() },
        onFavouritesTapped = { viewModel.getFavourites() }
    )
}

@Composable
private fun ListBody(
    state: IssueListState,
    onItemClicked: (Issue) -> Unit = {},
    onRefresh: () -> Unit = {},
    onFavouritesTapped: () -> Unit = {},
    onLastSeenTapped: () -> Unit = {},
    onAssignedToMeTapped: () -> Unit = {}
) {
    Scaffold(topBar = {
        TopBar(
            onRefresh = onRefresh,
            onFavouritesTapped = onFavouritesTapped,
            onAssignedToMeTapped = onAssignedToMeTapped,
            onLastSeenTapped = onLastSeenTapped
        )
    }) {
        BodyContent(
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
    onFavouritesTapped: () -> Unit,
    onLastSeenTapped: () -> Unit,
    onAssignedToMeTapped: () -> Unit
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

        val tab1 = @Composable { Text("Assigned") } to {
            onAssignedToMeTapped()
        }
        val tab2 = @Composable { Text("Seen") } to {
            onLastSeenTapped()
        }
        val tab3 = @Composable { Text("EV") } to {
            onFavouritesTapped()
        }

        Tabs(tabs = listOf(tab1, tab2, tab3))
    }
}

@Composable
private fun BodyContent(
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
        ListBody(state = state)
    }
}

