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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.presentation.components.SharedScaffold
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.preview_paramater.WorkLogDetailPreviewParameterProvider
import java.text.SimpleDateFormat

@Composable
fun WorkLogDetailScreen(
    navController: NavController,
    viewModel: WorkLogDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Content(
        state = state,
        onBack = { navController.popBackStack() }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Content(state: WorkLogDetailState, onBack: () -> Unit) {
    SharedScaffold(
        title = { Text("${state.item?.issueId}") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit"
                )
            }
        }
    ) {
        DetailBody(state = state)
    }
}

@Composable
fun DetailBody(state: WorkLogDetailState) {
    Box(modifier = Modifier.fillMaxWidth()) {
        state.item?.let {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    OLTextField(
                        value = it.issueId,
                        onValueChange = {},
                        readOnly = !state.isEditing,
                        placeholder = { Text("ID of the issue you've been working on") }
                    )

                    Spacer(Modifier.padding(8.dp))

                    OLTextField(
                        modifier = Modifier
                            .height(124.dp),
                        value = it.comment,
                        onValueChange = {},
                        readOnly = !state.isEditing,
                        placeholder = { Text("Describe the work you've been doing") }
                    )

                    Spacer(Modifier.padding(8.dp))

                    val simpleDateTime = SimpleDateFormat("dd MMM yy")
                    val dateString = simpleDateTime.format(it.dateWorked)
                    RowItems("Date", dateString, {}, !state.isEditing)

                    Spacer(Modifier.padding(8.dp))

                    RowItems(
                        text = "Worked",
                        value = state.item.timeSpent,
                        onValueChange = {},
                        readOnly = !state.isEditing
                    )

                    Spacer(Modifier.padding(8.dp))

                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
                        OutlinedButton(onClick = { /*TODO*/ }) {
                            Text(text = "Log Time")
                        }
                        ElevatedButton(onClick = { /*TODO*/ }) {
                            Text(text = "Log Time")
                        }
                        FilledTonalButton(onClick = { /*TODO*/ }) {
                            Text(text = "Log Time")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowItems(
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
    readOnly: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text,
            Modifier.weight(.3f)
        )

        OLTextField(
            modifier = Modifier.weight(.7f),
            value = value,
            onValueChange = onValueChange,
            readOnly = readOnly
        )
    }
}

@Composable
fun OLTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    readOnly: Boolean,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
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
    JiraLoggerTheme {
        Content(state) {}
    }
}