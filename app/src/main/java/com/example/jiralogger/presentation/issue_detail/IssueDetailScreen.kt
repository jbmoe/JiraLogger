package com.example.jiralogger.presentation.issue_detail

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.R
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.presentation.components.SharedScaffold
import com.example.jiralogger.presentation.components.Text
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.ImageFromUrl
import com.example.jiralogger.presentation.util.Screen
import com.example.jiralogger.presentation.util.preview_paramater.IssueDetailPreviewParameterProvider

@Composable
fun IssueDetailScreen(
    navController: NavController,
    viewModel: IssueDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Content(
        state = state,
        onBack = {
            navController.popBackStack()
        },
        onLogTime = {
            val route =
                Screen.WorkLogAddEdit.route + "?${Constants.PARAM_ISSUE_KEY}=${state.item?.key}"
            navController.navigate(route)
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Content(state: IssueDetailState, onBack: () -> Unit, onLogTime: (String?) -> Unit) {
    val scaffoldState = androidx.compose.material.rememberScaffoldState()
    SharedScaffold(
        state = scaffoldState,
        title = { Text("Detail") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        FAB = {
            ExtendedFloatingActionButton(
                icon = {
                    Icon(painter = painterResource(id = R.drawable.ic_baseline_more_time_24), "")
                },
                text = { Text(text = "Log your time") },
                onClick = { onLogTime(state.item?.key) }
            )
        }
    ) {
        DetailBody(state = state)
    }
}

@Composable
private fun DetailBody(state: IssueDetailState) {
    Box(modifier = Modifier.fillMaxSize()) {
        state.item?.let { issue ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    TitleContent(issue)
                    Spacer(modifier = Modifier.height(8.dp))
                    Summary(issue)
                    Spacer(modifier = Modifier.height(15.dp))
                    Description(issue)
                }
            }
        }
        if (state.error.isNotBlank()) {
            ErrorText(state, Modifier.align(Alignment.Center))
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun ErrorText(state: IssueDetailState, modifier: Modifier = Modifier) {
    Text(
        text = state.error,
        color = MaterialTheme.colorScheme.error,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    )
}

@Composable
private fun TitleContent(issue: Issue) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageFromUrl(
            url = issue.issuetype.iconUrl,
            placeholder = R.drawable.default_issuetype,
            contentDescription = "Issue Type",
            modifier = Modifier
                .padding(end = 4.dp)
                .size(16.dp)
        )
        Text(
            text = issue.key,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .weight(8f)
                .alpha(0.75f)
        )
    }
}

@Composable
private fun Summary(issue: Issue) {
    Text(
        text = issue.summary,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
private fun Description(issue: Issue) {
    if (issue.description != "") {
        Text(
            text = "Description",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = issue.description,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(IssueDetailPreviewParameterProvider::class) state: IssueDetailState) {
    JiraLoggerTheme {
        Content(state = state, {}) {}
    }
}