package com.example.jiralogger.presentation.issue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jiralogger.R
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.presentation.components.*
import com.example.jiralogger.presentation.util.ImageFromUrl
import com.example.jiralogger.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IssueScreen(
    navController: NavController,
    viewModel: IssueViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    CommonScaffold(
        navigationAction = CommonScaffoldNavigationActions.Back,
        onNavigationAction = { navController.popBackStack() },
        titleText = state.item?.key ?: "",
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { IconPablo(R.drawable.ic_baseline_more_time_24) },
                text = { PabloText(text = "Log your time") },
                onClick = {
                    val route =
                        Screen.WorkLogAddEdit.route + "?${Constants.PARAM_ISSUE_KEY}=${state.item?.key}"
                    navController.navigate(route)
                }
            )
        },
        navController = navController
    ) {
        DetailBody(state = state)
    }
}

@Composable
private fun DetailBody(state: IssueState) {
    Box(modifier = Modifier.fillMaxSize()) {
        state.item?.let { issue ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    TitleContent(issue)
                    Spacer(modifier = Modifier.height(8.dp))
                    Summary(issue)
                    Spacer(modifier = Modifier.height(15.dp))
                    Description(issue)
                }
            }
        }
        if (state.error.isNotBlank()) {
            ErrorText(state.error, Modifier.align(Alignment.Center))
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun TitleContent(issue: Issue) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageFromUrl(
            url = issue.issuetype.iconUrl,
            placeholder = R.drawable.default_issuetype,
            contentDescription = "Issue Type",
            modifier = Modifier
                .padding(end = 4.dp)
                .size(16.dp)
        )
        PabloText(
            text = issue.key,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .weight(8f)
                .alpha(0.75f)
        )
    }
}

@Composable
private fun Summary(issue: Issue) {
    PabloText(
        text = issue.summary,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
private fun Description(issue: Issue) {
    if (issue.description != "") {
        PabloText(
            text = "Description",
            style = MaterialTheme.typography.headlineSmall
        )
        PabloText(
            text = issue.description,
            style = MaterialTheme.typography.bodySmall
        )
    }
}