package com.example.jiralogger.presentation.work_log_add_edit

import android.annotation.SuppressLint
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
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.R
import com.example.jiralogger.presentation.components.DatePickerView
import com.example.jiralogger.presentation.components.SharedScaffold
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.preview_paramater.WorkLogDetailPreviewParameterProvider
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.*

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
    date: InputFieldState<Date>,
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
    date: InputFieldState<Date>,
    worked: InputFieldState<String>,
    onEvent: (AddEditWorkLogEvent) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                OLTextField(
                    value = issueId.value,
                    onValueChange = { onEvent(AddEditWorkLogEvent.IssueChosen(it)) },
                    onFocusChanged = { onEvent(AddEditWorkLogEvent.ChangeIssueFocus(it)) },
                    placeholder = { Text(issueId.hint) }
                )

                Spacer(Modifier.padding(8.dp))

                OLTextField(
                    modifier = Modifier
                        .height(124.dp),
                    value = description.value,
                    onValueChange = { onEvent(AddEditWorkLogEvent.EnteredDescription(it)) },
                    onFocusChanged = { onEvent(AddEditWorkLogEvent.ChangedDescriptionFocus(it)) },
                    placeholder = { Text(description.hint) }
                )

                Spacer(Modifier.padding(8.dp))

                DateRow(date, onEvent)

                Spacer(Modifier.padding(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Worked",
                        Modifier.weight(.3f)
                    )

                    OLTextField(
                        modifier = Modifier.weight(.7f),
                        value = worked.value,
                        onValueChange = { onEvent(AddEditWorkLogEvent.EnteredTimeSpent(it)) },
                        onFocusChanged = { onEvent(AddEditWorkLogEvent.ChangedTimeSpentFocus(it)) },
                        placeholder = { Text(worked.hint) }
                    )
                }
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
private fun DateRow(
    date: InputFieldState<Date>,
    onEvent: (AddEditWorkLogEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Date",
            Modifier.weight(.3f)
        )

        val formatter = SimpleDateFormat("dd. MMM yy")
        val displayDate = formatter.format(date.value)

        DatePickerView(modifier = Modifier.weight(.7f), datePicked = displayDate, updatedDate = {
            onEvent(AddEditWorkLogEvent.DateChosen(Date(it!!)))
        })
    }
}

@Composable
fun OLTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit,
    readOnly: Boolean = false,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged {
                onFocusChanged(it)
            },
        value = value,
        onValueChange = { onValueChange(it) },
        readOnly = readOnly,
        label = label,
        placeholder = placeholder,
        singleLine = singleLine,
        maxLines = maxLines,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Gray
        )
    )
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
            date = InputFieldState(Date(System.nanoTime())),
            worked = InputFieldState("2h 45m"),
            scaffoldState = rememberScaffoldState(),
            {},
            {}
        )
    }
}