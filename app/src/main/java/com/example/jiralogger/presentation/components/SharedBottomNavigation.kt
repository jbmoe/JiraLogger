package com.example.jiralogger.presentation.components

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiralogger.R
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme

@ExperimentalAnimationApi
@Composable
fun SharedBottomNavigation() {
    NavigationBar(tonalElevation = 4.dp) {
        var selectedIndex by remember { mutableStateOf(0) }
        NavigationBarItem(
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0 },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_assignment_24),
                    contentDescription = "Issues"
                )
            },
            label = {
                AnimatedVisibility(visible = selectedIndex == 0) {
                    Text("Issues", modifier = Modifier)
                }
            },
            alwaysShowLabel = false
        )
        NavigationBarItem(
            selected = selectedIndex == 1,
            onClick = { selectedIndex = 1 },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_access_time_filled_24),
                    contentDescription = "Work Logs"
                )
            },
            label = {
                AnimatedVisibility(visible = selectedIndex == 1) {
                    Text(text = "Work Logs")
                }
            },
            alwaysShowLabel = false
        )
    }
}

@ExperimentalAnimationApi
@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview() {
    JiraLoggerTheme {
        SharedBottomNavigation()
    }
}