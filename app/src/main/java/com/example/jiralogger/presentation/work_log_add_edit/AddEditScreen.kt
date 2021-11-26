package com.example.jiralogger.presentation.work_log_add_edit

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.R
import com.example.jiralogger.presentation.components.DatePicker
import com.example.jiralogger.presentation.components.NumberPicker
import com.example.jiralogger.presentation.components.OutlinedTextField
import com.example.jiralogger.presentation.components.SharedScaffold
import com.example.jiralogger.presentation.components.Text
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.preview_paramater.WorkLogDetailPreviewParameterProvider
import com.example.jiralogger.presentation.work_log_add_edit.component.IssueDropDown
import kotlinx.coroutines.flow.collectLatest

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun AddEditScreen(
    navController: NavController,
    viewModel: AddEditViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditViewModel.UiEvent.SaveLog -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Content(
        state = state,
        scaffoldState = scaffoldState,
        onBack = { navController.popBackStack() },
        onEvent = {
            viewModel.onEvent(it)
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun Content(
    state: AddEditState,
    scaffoldState: ScaffoldState,
    onBack: () -> Unit,
    onEvent: (AddEditEvent) -> Unit
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
                onEvent(AddEditEvent.Save)
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
            state = state,
            onEvent = onEvent
        )
    }
}

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DetailBody(
    state: AddEditState,
    onEvent: (AddEditEvent) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                IssueDropDown(
                    modifier = Modifier.fillMaxWidth(),
                    currentIssueId = state.issueId,
                    issuePicked = {
                        onEvent(AddEditEvent.IssueChosen(it.key))
                    }
                )

                Spacer(Modifier.padding(8.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(124.dp),
                    onValueChange = {
                        onEvent(AddEditEvent.EnteredDescription(it))
                    },
                    inputState = state.description
                )

                Spacer(Modifier.padding(8.dp))

                DatePicker(
                    modifier = Modifier.fillMaxWidth(),
                    selectedDate = state.date.value,
                    placeholderText = state.date.placeholder,
                    isError = state.date.isError
                ) {
                    onEvent(AddEditEvent.DateChosen(it))
                }

                Spacer(Modifier.padding(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Worked", Modifier.weight(.5f))
                    val color =
                        if (state.timeSpentSec.isError) MaterialTheme.colorScheme.error
                        else Color.Transparent
                    Box(
                        modifier = Modifier
                            .border(1.dp, color, shape = RoundedCornerShape(4.dp))
                            .weight(.5F)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            NumberPicker(
                                value = state.hoursSpent,
                                suffix = "h",
                                onChange = {
                                    onEvent(AddEditEvent.HoursChanged(it))
                                }
                            )
                            NumberPicker(
                                value = state.minutesSpent,
                                suffix = "m",
                                onChange = {
                                    onEvent(AddEditEvent.MinutesChanged(it))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun Preview(@PreviewParameter(WorkLogDetailPreviewParameterProvider::class) state: AddEditState) {
    JiraLoggerTheme {
        Content(
            state,
            scaffoldState = rememberScaffoldState(),
            {},
            {}
        )
    }
}