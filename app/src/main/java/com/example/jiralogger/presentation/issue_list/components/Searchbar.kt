package com.example.jiralogger.presentation.issue_list.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.jiralogger.presentation.issue_list.IssueListState
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.preview_paramater.IssueListPreviewParameterProvider

@Composable
fun Searchbar(onValueChange: (String) -> Unit) {
    var value by remember { mutableStateOf("") }
    Row(
        Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            value = value,
            onValueChange = {
                value = it
                onValueChange(value)
            },
            trailingIcon = {
                Icon(Icons.Default.Search, contentDescription = "")
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colorScheme.background,
                textColor = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}

@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(IssueListPreviewParameterProvider::class) state: IssueListState) {
    JiraLoggerTheme {
        Searchbar {}
    }
}