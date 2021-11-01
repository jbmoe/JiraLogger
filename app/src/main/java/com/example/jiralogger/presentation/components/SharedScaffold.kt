package com.example.jiralogger.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jiralogger.R
import com.example.jiralogger.presentation.issue_list.IssuesEvent
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.BottomNavItem
import com.example.jiralogger.presentation.util.Screen

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedScaffold(
    title: @Composable () -> Unit,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { SharedTopAppBar(title, navigationIcon, actions) },
        bottomBar = bottomBar
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}

@Composable
fun SharedTopAppBar(
    title: @Composable () -> Unit,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    SmallTopAppBar(
        title = {
            title()
        },
        actions = { actions() },
        navigationIcon = {
            navigationIcon()
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomNavigationBar(
    items: List<Screen>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (Screen) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(modifier = modifier.height(74.dp), tonalElevation = 0.dp) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { if (!selected) onItemClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.drawableId!!),
                        contentDescription = item.name
                    )
                },
                label = {
                    AnimatedVisibility(visible = selected) {
                        Text(item.name!!)
                    }
                },
                alwaysShowLabel = false
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun SharedBottomNavigation() {
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun Preview() {
    val navController = rememberNavController()
    JiraLoggerTheme {
        SharedScaffold(
            title = { Text(text = "Issues") },
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = "Refresh"
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_baseline_filter_alt_24),
                        contentDescription = "Filter"
                    )
                }
            },
            bottomBar = {
                BottomNavigationBar(
                    items = listOf(
                        Screen.IssueListScreen,
                        Screen.WorkLogListScreen
                    ),
                    navController = navController,
                    onItemClick = {
                        if (navController.currentBackStackEntry?.destination?.route != it.route)
                            navController.navigate(it.route)
                    }
                )
            }
        ) {

        }
    }
}