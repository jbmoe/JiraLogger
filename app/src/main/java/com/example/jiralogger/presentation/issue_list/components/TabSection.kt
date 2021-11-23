package com.example.jiralogger.presentation.issue_list.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiralogger.domain.util.HasName
import com.example.jiralogger.domain.util.IssueFilter
import com.example.jiralogger.presentation.components.Text
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme

@Composable
fun <T : HasName> TabSection(
    modifier: Modifier = Modifier,
    tabs: List<T>,
    currentTab: T,
    onTabChange: (T) -> Unit = {}
) {
    TabRow(
        selectedTabIndex = tabs.indexOf(currentTab),
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
            .height(40.dp)
            .shadow(6.dp)
    ) {
        tabs.forEach { filter ->
            Tab(
                modifier = Modifier.clickable { onTabChange(filter) },
                selected = currentTab == filter,
                onClick = { onTabChange(filter) }
            ) {
                Text(
                    filter.name,
                    color = MaterialTheme.colorScheme.onBackground,
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
            currentTab = IssueFilter.Assigned, tabs = listOf(
                IssueFilter.Assigned,
                IssueFilter.Seen,
                IssueFilter.Watching,
                IssueFilter.EV
            )
        )
    }
}