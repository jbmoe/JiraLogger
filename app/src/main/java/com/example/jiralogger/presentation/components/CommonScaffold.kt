package com.example.jiralogger.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jiralogger.presentation.components.CommonScaffoldNavigationActions.*
import com.example.jiralogger.presentation.util.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonScaffold(
    navigationAction: CommonScaffoldNavigationActions = None,
    onNavigationAction: () -> Unit = {},
    titleText: String,
    actions: @Composable RowScope.() -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    navController: NavController,
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    content: @Composable (PaddingValues) -> Unit
) {
    when (navigationAction) {
        Menu -> {
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            NavigationDrawer(
                drawerState = drawerState,
                drawerContent = {

                },
                content = {
                    Scaffold(
                        navigationActions = navigationAction,
                        onNavigationAction = {
                            scope.launch { drawerState.open() }
                        },
                        titleText = titleText,
                        actions = actions,
                        snackbarHost = snackbarHost,
                        navController = navController,
                        floatingActionButtonPosition = floatingActionButtonPosition,
                        floatingActionButton = floatingActionButton,
                    ) {
                        content(it)
                    }
                }
            )
        }
        else -> {
            Scaffold(
                navigationActions = navigationAction,
                onNavigationAction = onNavigationAction,
                titleText = titleText,
                actions = actions,
                snackbarHost = snackbarHost,
                navController = navController,
                floatingActionButtonPosition = floatingActionButtonPosition,
                floatingActionButton = floatingActionButton
            ) {
                content(it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Scaffold(
    navigationActions: CommonScaffoldNavigationActions = None,
    onNavigationAction: () -> Unit,
    titleText: String,
    actions: @Composable RowScope.() -> Unit,
    snackbarHost: @Composable () -> Unit,
    navController: NavController,
    floatingActionButton: @Composable () -> Unit,
    floatingActionButtonPosition: FabPosition,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationActions = navigationActions,
                onNavigationAction = onNavigationAction,
                titleText = titleText,
                actions = actions
            )
        },
        snackbarHost = snackbarHost,
        bottomBar = { NavBar(navController = navController) },
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition
    ) {
        content(it)
    }
}

@Composable
private fun TopAppBar(
    navigationActions: CommonScaffoldNavigationActions,
    onNavigationAction: () -> Unit,
    titleText: String,
    actions: @Composable RowScope.() -> Unit
) {
    SmallTopAppBar(
        title = { PabloText(text = titleText) },
        navigationIcon = {
            when (navigationActions) {
                None -> {
                    TODO()
                }
                Menu -> {
                    IconButton(onClick = onNavigationAction) {
                        IconPablo(imageVector = Icons.Default.Menu)
                    }
                }
                Back -> {
                    IconButton(onClick = onNavigationAction) {
                        IconPablo(imageVector = Icons.Default.ArrowBack)
                    }
                }
            }
        },
        actions = actions
    )
}

enum class CommonScaffoldNavigationActions {
    Menu, Back, None
}

@Composable
private fun NavBar(
    items: List<Screen> = listOf(
        Screen.Issues,
        Screen.WorkLogListScreen
    ),
    navController: NavController
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(modifier = Modifier.height(52.dp), tonalElevation = 0.dp) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { IconPablo(resourceId = item.drawableId!!) },
                alwaysShowLabel = false
            )
        }
    }
}

