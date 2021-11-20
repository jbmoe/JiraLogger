package com.example.jiralogger.presentation.issue_list.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiralogger.domain.util.IssueFilter
import com.example.jiralogger.presentation.components.Text
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme

@Composable
fun TabSection(
    modifier: Modifier = Modifier,
    filters: List<IssueFilter>,
    currentFilter: IssueFilter,
    onFilterChange: (IssueFilter) -> Unit = {}
) {
    TabRow(
        selectedTabIndex = filters.indexOf(currentFilter),
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = modifier
            .height(40.dp)
            .shadow(6.dp)
    ) {
        filters.forEach { filter ->
            Tab(
                selected = currentFilter == filter,
                onClick = {
                    onFilterChange(filter)
                }) {
                Text(
                    filter.name,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview() {
    JiraLoggerTheme {
        TabSection(
            currentFilter = IssueFilter.Assigned, filters = listOf(
                IssueFilter.Assigned,
                IssueFilter.Seen,
                IssueFilter.WATCHING,
                IssueFilter.EV
            )
        )
    }
}