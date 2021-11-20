package com.example.jiralogger.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme

@Composable
fun SharedList(
    isError: Boolean = false,
    errorText: String = "",
    isLoading: Boolean = false,
    listContent: LazyListScope.() -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listContent()
        }
        if (isError) {
            ErrorText(errorText, Modifier.align(Alignment.Center))
        }
        if (isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun ErrorText(errorText: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
//            .fillMaxSize()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 8.dp,
        shadowElevation = 2.dp,
        color = MaterialTheme.colorScheme.errorContainer
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¯\\_(ツ)_/¯"
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = errorText,
                color = MaterialTheme.colorScheme.onErrorContainer,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun PreviewS() {
    JiraLoggerTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            SharedList(errorText = "HTTP 400", isError = true, isLoading = false) {
                items(listOf(1..99)) {
                    Text(text = it.toString())
                }
            }
        }
    }
}