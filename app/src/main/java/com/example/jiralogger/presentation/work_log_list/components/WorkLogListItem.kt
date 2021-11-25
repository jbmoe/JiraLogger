package com.example.jiralogger.presentation.work_log_list.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiralogger.R
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.domain.model.WorkLog
import com.example.jiralogger.presentation.components.Text
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WorkLogListItem(workLog: WorkLog, onItemClicked: (WorkLog) -> Unit, onDelete: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 4.dp,
        shadowElevation = 4.dp
    ) {
        ListItem(
            modifier = Modifier
                .clickable { onItemClicked(workLog) },
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = workLog.comment,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    IconButton(modifier = Modifier.size(18.dp), onClick = onDelete) {
                        Icon(Icons.Default.Delete, "Delete")
                    }
                }
            },
            secondaryText = {
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_check_box_24),
                        contentDescription = "",
                        tint = Color(1, 160, 100),
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Top)
                    )
                    Spacer(Modifier.padding(4.dp))
                    Text(
                        text = workLog.issueId,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .alpha(.8f)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = workLog.timeSpent,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Bottom)
                    )
                }
            }
        )
    }
}

@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview() {
    val item = TestData.WORK_LOG_TEST_DATA.random()
    JiraLoggerTheme {
        WorkLogListItem(workLog = item, onItemClicked = {},{})
    }
}