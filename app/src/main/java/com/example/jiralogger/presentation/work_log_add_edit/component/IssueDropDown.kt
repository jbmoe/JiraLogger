package com.example.jiralogger.presentation.work_log_add_edit.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jiralogger.R
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.presentation.components.OutlinedTextField
import com.example.jiralogger.presentation.components.Text
import com.example.jiralogger.presentation.issue_list.IssueListViewModel
import com.example.jiralogger.presentation.issue_list.IssuesEvent
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.ImageFromUrl
import com.example.jiralogger.presentation.util.InputFieldState

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IssueDropDown(
    modifier: Modifier = Modifier,
    currentIssueId: InputFieldState<String>,
    issuePicked: (Issue) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            modifier = modifier,
            readOnly = true,
            value = currentIssueId.value,
            onValueChange = { },
            labelText = currentIssueId.label,
            placeholderText = currentIssueId.placeholder,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            }
        )
        Menu(expanded = expanded, onDismiss = { expanded = false }, onSelect = issuePicked)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
private fun ExposedDropdownMenuBoxScope.Menu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    viewModel: IssueListViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
    onSelect: (Issue) -> Unit
) {
    val background = MaterialTheme.colorScheme.background.copy(.9f)
    ExposedDropdownMenu(
        modifier = modifier.background(background),
        expanded = expanded,
        onDismissRequest = onDismiss
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            viewModel.filters.onEach { filter ->
                DefaultRadioButton(
                    text = filter.name,
                    selected = filter == viewModel.state.value.issueFilter,
                    onSelect = { viewModel.onEvent(IssuesEvent.Filter(filter)) })
            }
        }
        Column(modifier = modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                modifier = modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.onBackground.copy(.7f)
            )
            viewModel.state.value.items.forEach { issue ->
                DropdownMenuItem(
                    onClick = {
                        onDismiss()
                        onSelect(issue)
                    }
                ) {
                    ListItem(issue = issue)
                }
            }
            if (viewModel.state.value.isLoading) {
                CircularProgressIndicator(Modifier.align(CenterHorizontally))
            }
        }
    }
}

@Composable
private fun ListItem(modifier: Modifier = Modifier, issue: Issue) {
    Row(modifier = modifier.padding(8.dp)) {
        ImageFromUrl(
            url = issue.issuetype.iconUrl,
            placeholder = R.drawable.default_issuetype,
            contentDescription = "Issue Type",
            modifier = Modifier
                .padding(4.dp)
                .size(18.dp)
        )
        Column(modifier = modifier.padding(horizontal = 4.dp)) {
            Text(text = issue.key)
            Text(
                text = issue.summary,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onBackground.copy(.6f)
            )
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun Preview() {
    val issue = InputFieldState(
        value = "DAL-656",
        label = "Issue",
        placeholder = "Search issues..."
    )
    JiraLoggerTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            IssueDropDown(currentIssueId = issue, issuePicked = {})
        }
    }
}