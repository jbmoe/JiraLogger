package com.example.jiralogger.presentation.issues

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.domain.util.IssueFilter
import com.example.jiralogger.presentation.components.*
import com.example.jiralogger.presentation.issues.components.IssueItem
import com.example.jiralogger.presentation.issues.components.Searchbar
import com.example.jiralogger.presentation.issues.components.TabSection
import com.example.jiralogger.presentation.util.Screen

@Composable
fun IssuesScreen(
    navController: NavController,
    viewModel: IssuesViewModel = hiltViewModel()
) {
    viewModel.run {
        Content(state.value, viewModel.issueFilters, navController) {
            onEvent(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
private fun Content(
    state: IssuesState,
    filters: List<IssueFilter>,
    navController: NavController,
    onEvent: (IssuesEvent) -> Unit,
) {
    var searchIsVisible by remember { mutableStateOf(value = false) }
    CommonScaffold(
        titleText = "Issues",
        navigationAction = CommonScaffoldNavigationActions.Menu,
        actions = {
            IconButton(onClick = { searchIsVisible = !searchIsVisible }) {
                IconPablo(imageVector = Icons.Default.Search)
            }
            IconButton(onClick = { onEvent(IssuesEvent.OnRefresh) }) {
                IconPablo(imageVector = Icons.Default.Refresh)
            }
        },
        navController = navController
    ) {
        Column(modifier = Modifier.padding(it)) {
            val fadein = fadeIn() + scaleIn() + expandVertically()
            val fadeout = fadeOut() + scaleOut() + shrinkVertically()
            AnimatedVisibility(visible = searchIsVisible, enter = fadein, exit = fadeout) {
                Searchbar {
                    onEvent(IssuesEvent.OnSearch(IssueFilter.SEARCH(it)))
                }
            }
            AnimatedVisibility(visible = !searchIsVisible, enter = fadein, exit = fadeout) {
                TabSection(
                    tabs = filters,
                    currentTab = state.issueFilter
                ) {
                    onEvent(IssuesEvent.OnFilterChanged(it))
                }
            }
            Box(modifier = Modifier.fillMaxSize()) {
                CommonLazyList {
                    items(state.items) { issue ->
                        IssueItem(issue = issue) {
                            navController.navigate(route = "${Screen.Issue.route}/${issue.key}")
                        }
                    }
                }
                if (state.isError) {
                    ErrorText(errorText = state.error, Modifier.align(Alignment.Center))
                }
                if (state.isLoading) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        }
    }
}