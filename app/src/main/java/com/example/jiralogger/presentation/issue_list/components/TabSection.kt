package com.example.jiralogger.presentation.issue_list.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiralogger.domain.util.IssueFilter

@Composable
fun TabSection(
    modifier: Modifier = Modifier,
    onFilterChange: (IssueFilter) -> Unit
) {
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier
            .height(40.dp)
            .shadow(6.dp)
    ) {
        Tab(
            selected = selectedIndex == 0,
            onClick = {
                selectedIndex = 0
                onFilterChange(IssueFilter.Assigned)
            }) {
            Text("Assigned")
        }
        Tab(
            selected = selectedIndex == 1,
            onClick = {
                selectedIndex = 1
                onFilterChange(IssueFilter.Seen)
            }) {
            Text("Seen")
        }
        Tab(
            selected = selectedIndex == 2,
            onClick = {
                selectedIndex = 2
                onFilterChange(IssueFilter.EV)
            }) {
            Text("EV")
        }
    }
}

@Preview
@Composable
fun Preview() {
    TabSection(onFilterChange = {})
}