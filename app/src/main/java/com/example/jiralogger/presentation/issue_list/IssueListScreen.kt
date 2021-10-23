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
import com.example.jiralogger.common.TestData
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.presentation.Screen
import com.example.jiralogger.presentation.issue_list.components.IssueListItem
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme

@Composable
fun IssueListScreen(
    navController: NavController,
    viewModel: IssueListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    ListBody(state) { issue ->
        navController.navigate(Screen.IssueDetailScreen.route + "/${issue.key}")
    }
}

@Composable
private fun ListBody(
    state: IssueListState,
    onItemClicked: (Issue) -> Unit
) {
    Scaffold(topBar = { TopBar() }) {
        BodyContent(state) { issue ->
            onItemClicked(issue)
        }
    }
}

@Composable
private fun TopBar() {
//    var isExpanded by remember { mutableStateOf(false) }
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
                    onItemClicked = {
                        onItemClicked(issue)
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
    JiraLoggerTheme {
        val state = IssueListState(issues = TestData.API_RESULT_TEST_OBJECT.toIssuesList())
        ListBody(state = state, onItemClicked = {})
    }
}

