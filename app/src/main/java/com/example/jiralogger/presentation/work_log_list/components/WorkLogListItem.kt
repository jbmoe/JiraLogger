package com.example.jiralogger.presentation.work_log_list.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.preview_paramater.WorkLogDetailPreviewParameterProvider
import com.example.jiralogger.presentation.work_log_detail.WorkLogDetailState
import java.text.SimpleDateFormat

@Composable
fun WorkLogListItem(workLog: WorkLog, onItemClicked: (WorkLog) -> Unit) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 8.dp,
        shadowElevation = 8.dp
    ) {
        Column(modifier = Modifier
            .clickable { onItemClicked(workLog) }
            .padding(8.dp)
            .fillMaxWidth()
        ) {
            Text(
                text = workLog.issueId,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = workLog.timeSpent,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )

            val simpleDateTime = SimpleDateFormat("dd MMM yy")
            val dateString = simpleDateTime.format(workLog.dateWorked)

            Text(
                text = dateString,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Text(
                text = workLog.comment,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(WorkLogDetailPreviewParameterProvider::class) state: WorkLogDetailState) {
    JiraLoggerTheme {
        WorkLogListItem(workLog = state.item as WorkLog, onItemClicked = {})
    }
}