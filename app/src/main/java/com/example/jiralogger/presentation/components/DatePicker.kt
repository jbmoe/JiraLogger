package com.example.jiralogger.presentation.components

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.jiralogger.presentation.issue_list.IssueListState
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.ui.theme.outlinedTextFieldColors
import com.example.jiralogger.presentation.util.preview_paramater.IssueListPreviewParameterProvider
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
    selectedDate: Long,
    activity: AppCompatActivity = LocalContext.current as AppCompatActivity,
    dateFormat: String = "E d. MMM yy",
    datePicked: (Long) -> Unit
) {
    val sdf = SimpleDateFormat(dateFormat)
    val display = sdf.format(Date(selectedDate))

    OutlinedTextField(
        modifier = modifier,
        value = display,
        onValueChange = {

        },
        trailingIcon = {
            IconButton(onClick = {
                showDatePicker(activity = activity, selectedDate = selectedDate) {
                    datePicked(it)
                }
            }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Calendar",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        readOnly = true,
    )
}

private fun showDatePicker(
    selectedDate: Long,
    activity: AppCompatActivity,
    datePicked: (Long) -> Unit
) {
    val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Select date")
        .setSelection(selectedDate)
        .build()

    datePicker.addOnPositiveButtonClickListener {
        datePicked(it)
    }

    datePicker.show(activity.supportFragmentManager, datePicker.toString())
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(IssueListPreviewParameterProvider::class) state: IssueListState) {
    JiraLoggerTheme {

    }
}