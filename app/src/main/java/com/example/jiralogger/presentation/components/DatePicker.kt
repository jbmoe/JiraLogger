package com.example.jiralogger.presentation.components

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.jiralogger.presentation.issue_list.IssueListState
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.preview_paramater.IssueListPreviewParameterProvider
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
    selectedDate: Long,
    placeholderText: String = "",
    isError: Boolean = false,
    activity: AppCompatActivity = LocalContext.current as AppCompatActivity,
    dateFormat: String = "E dd MMMM yyyy",
    datePicked: (Long) -> Unit
) {
    val sdf = SimpleDateFormat(dateFormat)
    val display = if (selectedDate != 0L) sdf.format(Date(selectedDate)) else ""

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            modifier = modifier,
            value = display,
            labelText = "Date",
            placeholderText = placeholderText,
            isError = isError,
            onValueChange = {

            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Calendar",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            },
            readOnly = true,
        )
    }
    if (expanded) {
        showDatePicker(
            activity = activity,
            selectedDate = if (selectedDate != 0L) selectedDate else System.currentTimeMillis(),
            onDismiss = { expanded = false }) {
            datePicked(it)
        }
    }
}

private fun showDatePicker(
    selectedDate: Long,
    activity: AppCompatActivity,
    onDismiss: () -> Unit,
    datePicked: (Long) -> Unit
) {
    val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Select date")
        .setSelection(selectedDate)
        .build()

    datePicker.addOnPositiveButtonClickListener {
        datePicked(it)
    }

    datePicker.addOnDismissListener {
        onDismiss()
    }

    datePicker.show(activity.supportFragmentManager, datePicker.toString())
}

@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(IssueListPreviewParameterProvider::class) state: IssueListState) {
    JiraLoggerTheme {
        DatePicker(selectedDate = System.currentTimeMillis(), datePicked = {})
    }
}