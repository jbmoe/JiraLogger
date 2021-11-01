package com.example.jiralogger.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.presentation.issue_list.components.IssueListItem
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.UiListState
import com.example.jiralogger.presentation.util.preview_paramater.IssueListPreviewParameterProvider

@Composable
fun SharedList(
    modifier: Modifier = Modifier,
    state: UiListState,
    listItem: @Composable (Any?) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.items) { item ->
                listItem(item)
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
private fun ErrorText(state: UiListState, modifier: Modifier = Modifier) {
    Text(
        text = state.error,
        color = MaterialTheme.colorScheme.error,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    )
}
