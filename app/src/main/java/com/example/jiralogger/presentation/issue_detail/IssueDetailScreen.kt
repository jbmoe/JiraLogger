package com.example.jiralogger.presentation.issue_detail

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jiralogger.common.TestData
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme

@Composable
fun IssueDetailScreen(
    viewModel: IssueDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    DetailBody(state)
}

@Composable
private fun DetailBody(state: IssueDetailState) {
    Box(modifier = Modifier.fillMaxSize()) {
        state.issue?.let { issue ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${issue.projectName} / ${issue.key}",
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.weight(8f)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "${issue.summary}",
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.h3
                    )
                    Text(
                        text = "${issue.description}",
                        style = MaterialTheme.typography.body2
                    )
                }
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

@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview() {
    JiraLoggerTheme {
        DetailBody(state = IssueDetailState(issue = TestData.ISSUE_TEST_OBJECT))
    }
}