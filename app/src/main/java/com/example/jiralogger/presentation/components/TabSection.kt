package com.example.jiralogger.presentation.issues.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.jiralogger.domain.util.Tabable
import com.example.jiralogger.presentation.components.PabloText

@Composable
fun <T : Tabable> TabSection(
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
        tabs.forEach { tab ->
            Tab(
                modifier = Modifier.clickable { onTabChange(tab) },
                selected = currentTab == tab,
                onClick = { onTabChange(tab) }
            ) {
                PabloText(
                    tab.name,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}