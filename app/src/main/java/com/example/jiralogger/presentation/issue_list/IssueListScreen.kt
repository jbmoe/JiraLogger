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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.common.Filters
import com.example.jiralogger.common.TestData
import com.example.jiralogger.presentation.Screen
import com.example.jiralogger.presentation.issue_list.components.IssueListItem

@Composable
fun IssueListScreen(
    navController: NavController,
    viewModel: IssueListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    List(state, navController)
}

@Composable
private fun List(
    state: IssueListState,
    navController: NavController
) {
    Scaffold(topBar = { TopBar() }) {
        BodyContent(state, navController)
    }
}

@Composable
private fun TopBar(viewModel: IssueListViewModel = hiltViewModel()) {
    var isExpanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(text = "Issues")
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
            }
            IconButton(onClick = { isExpanded = true }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "More")
                DropDownMenu(
                    expanded = isExpanded,
                    onAssignedClick = {
                        isExpanded = false
                        viewModel.getFilteredIssues(Filters.FILTER_ASSIGNED_TO_ME)
                    },
                    onLastSeenClick = {
                        isExpanded = false
                        viewModel.getFilteredIssues(Filters.LAST_SEEN)
                    },
                    onReportedClick = {
                        isExpanded = false
                        viewModel.getFilteredIssues(Filters.REPORTED_BY_ME)
                    },
                    onDisMiss = { isExpanded = false }
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        }
    )
}

@Composable
private fun DropDownMenu(
    expanded: Boolean,
    onAssignedClick: () -> Unit,
    onReportedClick: () -> Unit,
    onLastSeenClick: () -> Unit,
    onDisMiss: () -> Unit,
) {
    DropdownMenu(expanded = expanded, onDismissRequest = onDisMiss) {
        DropdownMenuItem(onClick = onAssignedClick) {
            Text("Assigned To Me")
        }
        DropdownMenuItem(onClick = onReportedClick) {
            Text("Reported By Me")
        }
        DropdownMenuItem(onClick = onLastSeenClick) {
            Text("Last Seen")
        }
    }
}

@Composable
private fun BodyContent(
    state: IssueListState,
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.issues) { issue ->
                IssueListItem(
                    issue = issue!!,
                    onItemClicked = {
                        navController.navigate(Screen.IssueDetailScreen.route + "/${issue.key}")
                    }
                )
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )
            }

        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(name = "Light mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview() {
    List(
        navController = NavController(LocalContext.current),
        state = IssueListState(issues = TestData.API_RESULT_TEST_OBJECT.toIssuesList())
    )
}

