package com.example.jiralogger.presentation.issue_list.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun Tabs(
    tabs: List<Pair<@Composable () -> Unit, () -> Unit>>
) {
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = Modifier
            .height(40.dp)
            .shadow(elevation = 6.dp, clip = true),
    ) {
        tabs.forEachIndexed { index, pair ->
            val content = pair.first
            val onClick = pair.second
            Tab(selected = selectedIndex == index, onClick = {
                selectedIndex = index
                onClick()
            }) {
                content()
            }
        }
    }
}