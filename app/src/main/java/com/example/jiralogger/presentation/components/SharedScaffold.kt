package com.example.jiralogger.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jiralogger.R
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.Screen

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedScaffold(
    state: ScaffoldState,
    title: @Composable () -> Unit,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    FAB: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
//    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = state,
        topBar = { SharedTopAppBar(title, navigationIcon, actions) },
        bottomBar = bottomBar,
        floatingActionButton = { FAB() }
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

@ExperimentalMaterial3Api
@OptIn(ExperimentalAnimationApi::class)
@Composable
@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun Preview() {
    val navController = rememberNavController()
    JiraLoggerTheme {
        SharedScaffold(
            state = rememberScaffoldState(),
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
            },
            FAB = {
                ExtendedFloatingActionButton(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_more_time_24),
                            ""
                        )
                    },
                    text = {
                        Text(text = "Log your time")
                    },
                    onClick = { }
                )
            }
        ) {

        }
    }
}