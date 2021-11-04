package com.example.jiralogger.presentation.work_log_detail

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.presentation.components.SharedScaffold
import com.example.jiralogger.presentation.util.preview_paramater.WorkLogDetailPreviewParameterProvider
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@Composable
fun WorkLogDetailScreen(
    navController: NavController,
    logViewModel: AddEditLogViewModel = hiltViewModel()
) {
    val issueId = logViewModel.issueId.value
    val description = logViewModel.description.value
    val date = logViewModel.date.value
    val timeSpent = logViewModel.timeSpent.value
    val timeSpentSec = logViewModel.timeSpentSec.value

    LaunchedEffect(key1 = true) {
        logViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditLogViewModel.UiEvent.ShowSnackbar -> {

                }
                is AddEditLogViewModel.UiEvent.SaveLog -> {

                }
            }
        }
    }

    Content(
        issueId = issueId,
        description = description,
        date = date,
        worked = timeSpent,
        onBack = { navController.popBackStack() },
        onEvent = {
            logViewModel.onEvent(it)
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Content(
    issueId: InputFieldState<String>,
    description: InputFieldState<String>,
    date: InputFieldState<Date>,
    worked: InputFieldState<String>,
    onBack: () -> Unit,
    onEvent: (AddEditWorkLogEvent) -> Unit
) {
    SharedScaffold(
        title = { Text(issueId.value ?: "Log your time") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
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
                    value = issueId.value ?: "",
                    onValueChange = {
                        onEvent(AddEditWorkLogEvent.IssueChosen(it))
                    },
                    onFocusChanged = {
                        onEvent(AddEditWorkLogEvent.ChangeIssueFocus(it))
                    },
                    placeholder = { Text(issueId.hint) }
                )

                Spacer(Modifier.padding(8.dp))

                OLTextField(
                    modifier = Modifier
                        .height(124.dp),
                    value = description.value ?: "",
                    onValueChange = { onEvent(AddEditWorkLogEvent.EnteredDescription(it)) },
                    onFocusChanged = { onEvent(AddEditWorkLogEvent.ChangedDescriptionFocus(it)) },
                    placeholder = { Text(description.hint) }
                )

//                Spacer(Modifier.padding(8.dp))
//
//                val simpleDateTime = SimpleDateFormat("dd MMM yy")
//                val dateString = simpleDateTime.format(date.value)
//
//                RowItems(
//                    text = "Date",
//                    value = dateString,
//                    onValueChange = { onEvent(AddEditWorkLogEvent.DateChosen(it)) },
//                    onFocusChanged = { onEvent(AddEditWorkLogEvent.ChangedDateFocus(it)) },
//                )
//
//                Spacer(Modifier.padding(8.dp))
//
//                RowItems(
//                    text = "Worked",
//                    value = worked.text,
//                    onValueChange = { onEvent(AddEditWorkLogEvent.EnteredTimeSpent(it)) },
//                    readOnly = !state.isEditing,
//                    onFocusChanged = { onEvent(AddEditWorkLogEvent.ChangedTimeSpentFocus(it)) }
//                )
//
//                Spacer(Modifier.padding(8.dp))
//
//                Row(Modifier.fillMaxWidth(), Arrangement.SpaceAround) {
//                    if (state.isEditing) {
//                        OutlinedButton(onClick = { onEvent(AddEditWorkLogEvent.Cancel) }) {
//                            ButtonContent(Icons.Default.Close, "Cancel")
//                        }
//                        FilledTonalButton(onClick = { onEvent(AddEditWorkLogEvent.Save) }) {
//                            ButtonContent(Icons.Default.CheckCircle, "Save")
//                        }
//                    }
//                }
            }
        }
    }
}

@Composable
private fun ButtonContent(icon: ImageVector, text: String) {
    Icon(icon, "")
    Spacer(Modifier.padding(4.dp))
    Text(text = text)
}

@Composable
fun RowItems(
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text,
            Modifier.weight(.3f)
        )

        OLTextField(
            modifier = Modifier.weight(.7f),
            value = value,
            onValueChange = { onValueChange(it) },
            onFocusChanged = { onFocusChanged(it) }
        )
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
            backgroundColor = Color.Gray,
            textColor = Color.LightGray
        )
    )
}

@Composable
@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun Preview(@PreviewParameter(WorkLogDetailPreviewParameterProvider::class) state: WorkLogDetailState) {
//    val state = WorkLogDetailState(
//        item = TestData.WORK_LOG_TEST_DATA[0],
//        isEditing = true
//    )
//    JiraLoggerTheme {
//        Content(state, {}, {})
//    }
}