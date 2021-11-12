package com.example.jiralogger.presentation.work_log_add_edit

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.R
import com.example.jiralogger.presentation.components.DatePicker
import com.example.jiralogger.presentation.components.NumberPickz
import com.example.jiralogger.presentation.components.SharedScaffold
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.preview_paramater.WorkLogDetailPreviewParameterProvider
import kotlinx.coroutines.flow.collectLatest

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun AddEditScreen(
    navController: NavController,
    viewModel: AddEditLogViewModel = hiltViewModel()
) {
    val issueId = viewModel.issueId.value
    val description = viewModel.description.value
    val date = viewModel.date.value
    val timeSpent = viewModel.timeSpent.value

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditLogViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditLogViewModel.UiEvent.SaveLog -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Content(
        issueId = issueId,
        description = description,
        date = date,
        worked = timeSpent,
        scaffoldState = scaffoldState,
        onBack = { navController.popBackStack() },
        onEvent = {
            viewModel.onEvent(it)
        }
    )
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun Content(
    issueId: InputFieldState<String>,
    description: InputFieldState<String>,
    date: InputFieldState<Long>,
    worked: InputFieldState<String>,
    scaffoldState: ScaffoldState,
    onBack: () -> Unit,
    onEvent: (AddEditWorkLogEvent) -> Unit
) {
    SharedScaffold(
        title = { Text("Log your time") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        FAB = {
            FloatingActionButton(onClick = {
                onEvent(AddEditWorkLogEvent.Save)
            }) {
                Icon(
                    painterResource(id = R.drawable.ic_baseline_save_24),
                    contentDescription = "Save"
                )
            }
        },
        state = scaffoldState
    ) {
        DetailBody(
            issueId = issueId,
            description = description,
            date = date,
            worked = worked,
            onEvent = onEvent
        )
    }
}

@Composable
fun DetailBody(
    issueId: InputFieldState<String>,
    description: InputFieldState<String>,
    date: InputFieldState<Long>,
    worked: InputFieldState<String>,
    onEvent: (AddEditWorkLogEvent) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { onEvent(AddEditWorkLogEvent.ChangeIssueFocus(it)) },
                    value = issueId.value,
                    onValueChange = { onEvent(AddEditWorkLogEvent.IssueChosen(it)) },
                    placeholder = {
                        Text(
                            issueId.hint,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(
                            "Issue ID",
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    readOnly = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colorScheme.background,
                        textColor = MaterialTheme.colorScheme.onBackground
                    )
                )

                Spacer(Modifier.padding(8.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(124.dp)
                        .onFocusChanged { onEvent(AddEditWorkLogEvent.ChangedDescriptionFocus(it)) },
                    value = description.value,
                    onValueChange = { onEvent(AddEditWorkLogEvent.EnteredDescription(it)) },
                    placeholder = {
                        Text(
                            description.hint,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(
                            "Comment",
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colorScheme.background,
                        textColor = MaterialTheme.colorScheme.onBackground
                    )
                )

                Spacer(Modifier.padding(8.dp))

                DateRow(date, onEvent)

                Spacer(Modifier.padding(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Worked",
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    val hours = (0..99).toList()
                    val minutes = listOf(0, 15, 30, 45)

                    NumberPickz(numbers = hours, suffix = "h", onChange = {})
                    NumberPickz(numbers = minutes, suffix = "m", onChange = {})

//                    NumberPicker(
//                        state = remember { mutableStateOf(0) },
//                        range = hours,
//                        labelSuffix = "h"
//                    )
//                    NumberPicker(
//                        state = remember { mutableStateOf(0) },
//                        range = minutes,
//                        labelSuffix = "m"
//                    )


//                    OutlinedTextField(
//                        modifier = Modifier
//                            .weight(.7f)
//                            .onFocusChanged {
//                                onEvent(AddEditWorkLogEvent.ChangedTimeSpentFocus(it))
//                            },
//                        value = worked.value,
//                        onValueChange = { onEvent(AddEditWorkLogEvent.EnteredTimeSpent(it)) },
//                        placeholder = {
//                            Text(
//                                worked.hint,
//                                color = MaterialTheme.colorScheme.onBackground
//                            )
//                        },
//                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = MaterialTheme.colorScheme.background,
//                            textColor = MaterialTheme.colorScheme.onBackground
//                        )
//                    )
                }
            }
        }
    }
}

@Composable
private fun DateRow(
    date: InputFieldState<Long>,
    onEvent: (AddEditWorkLogEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Date",
            Modifier.weight(.3f),
            color = MaterialTheme.colorScheme.onBackground
        )
        DatePicker(modifier = Modifier.weight(.7f), selectedDate = date.value) {
            onEvent(AddEditWorkLogEvent.DateChosen(it))
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun Preview(@PreviewParameter(WorkLogDetailPreviewParameterProvider::class) state: WorkLogDetailState) {
    JiraLoggerTheme {
        Content(
            issueId = InputFieldState(value = "DAL-656"),
            description = InputFieldState(value = "Working on issue DAL-656"),
            date = InputFieldState(value = System.nanoTime()),
            worked = InputFieldState("2h 45m"),
            scaffoldState = rememberScaffoldState(),
            {},
            {}
        )
    }
}