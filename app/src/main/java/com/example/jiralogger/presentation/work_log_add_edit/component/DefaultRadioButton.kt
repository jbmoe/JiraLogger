package com.example.jiralogger.presentation.work_log_add_edit.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiralogger.domain.util.IssueFilter
import com.example.jiralogger.presentation.components.PabloText
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme

@Composable
fun DefaultRadioButton(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color = if (selected) {
        MaterialTheme.colorScheme.primary.copy(alpha = ContentAlpha.high)
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
    }
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.clickable { onSelect() },
        border = BorderStroke(2.dp, color),
        color = Color.Transparent
    ) {
        PabloText(
            modifier = modifier.padding(8.dp), text = text
        )
    }
}


@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewDRB() {
    val filters =
        listOf(IssueFilter.Assigned, IssueFilter.Seen, IssueFilter.Watching, IssueFilter.EV)
    JiraLoggerTheme {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            filters.onEach { filter ->
                DefaultRadioButton(
                    text = filter.name,
                    selected = filter == IssueFilter.Seen,
                    onSelect = { }
                )
            }
        }
    }
}