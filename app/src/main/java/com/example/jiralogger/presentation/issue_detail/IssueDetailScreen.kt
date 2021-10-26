package com.example.jiralogger.presentation.issue_detail

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jiralogger.R
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.presentation.util.preview_paramater.IssueDetailPreviewParameterProvider
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.ImageFromUrl

@Composable
fun IssueDetailScreen(
    viewModel: IssueDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state = viewModel.state.value
    DetailBody(state = state, onBack = onBack)
}

@Composable
private fun DetailBody(state: IssueDetailState, onBack: () -> Unit) {
    Scaffold(topBar = { TopBar(title = "${state.issue?.key}", onBack = onBack) }) {
        Box(modifier = Modifier.fillMaxSize()) {
            state.issue?.let { issue ->
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
}

@Composable
private fun TopBar(title: String, onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
private fun ErrorText(state: IssueDetailState, modifier: Modifier = Modifier) {
    Text(
        text = state.error,
        color = MaterialTheme.colors.error,
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
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onBackground,
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
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.colors.onBackground,
    )
}

@Composable
private fun Description(issue: Issue) {
    Text(
        text = "Description",
        style = MaterialTheme.typography.h3,
        color = MaterialTheme.colors.onBackground
    )
    Text(
        text = issue.description,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.onBackground
    )
}

@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(IssueDetailPreviewParameterProvider::class) state: IssueDetailState) {
    JiraLoggerTheme {
        DetailBody(state = state) {}
    }
}