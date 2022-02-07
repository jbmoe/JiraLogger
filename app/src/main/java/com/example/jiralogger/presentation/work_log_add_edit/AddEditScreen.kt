package com.example.jiralogger.presentation.work_log_add_edit

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.R
import com.example.jiralogger.presentation.components.*
import com.example.jiralogger.presentation.work_log_add_edit.component.IssueDropDown
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun AddEditScreen(
    navController: NavController,
    viewModel: AddEditViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditViewModel.UiEvent.ShowSnackbar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = event.message
                        )
                    }
                }
                is AddEditViewModel.UiEvent.SaveLog -> {
                    navController.navigateUp()
                }
            }
        }
    }

    CommonScaffold(
        titleText = "Log your time",
        navigationAction = CommonScaffoldNavigationActions.Back,
        onNavigationAction = { navController.popBackStack() },
        navController = navController,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditEvent.Save)
            }) {
                IconPablo(R.drawable.ic_baseline_save_24)
            }
        }
    ) {
        Content(
            state = state,
            onEvent = { viewModel.onEvent(it) }
        )
    }
}

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Content(
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

                PabloTF(
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
                    PabloText("Worked", Modifier.weight(.5f))
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
