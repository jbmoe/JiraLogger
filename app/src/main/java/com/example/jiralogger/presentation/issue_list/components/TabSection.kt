package com.example.jiralogger.presentation.issue_list.components

import android.content.res.Configuration
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
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme

@Composable
fun TabSection(
    modifier: Modifier = Modifier,
    onFilterChange: (IssueFilter) -> Unit = {}
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
                onFilterChange(IssueFilter.A_Assigned)
            }) {
            Text(IssueFilter.A_Assigned.name)
        }
        Tab(
            selected = selectedIndex == 1,
            onClick = {
                selectedIndex = 1
                onFilterChange(IssueFilter.B_Seen)
            }) {
            Text(IssueFilter.B_Seen.name)
        }
        Tab(
            selected = selectedIndex == 2,
            onClick = {
                selectedIndex = 2
                onFilterChange(IssueFilter.C_WATCHING)
            }) {
            Text(IssueFilter.C_WATCHING.name)
        }
        Tab(
            selected = selectedIndex == 3,
            onClick = {
                selectedIndex = 3
                onFilterChange(IssueFilter.D_EV)
            }) {
            Text(IssueFilter.D_EV.name)
        }
//        IssueFilter::class.nestedClasses.forEachIndexed { i, it ->
//            val element = it.objectInstance as IssueFilter
//            Tab(
//                selected = selectedIndex == i,
//                onClick = {
//                    selectedIndex = i
//                    onFilterChange(element)
//                }) {
//                Text(element.name)
//            }
//        }
    }
}

@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview() {
    JiraLoggerTheme {
        TabSection()
    }
}