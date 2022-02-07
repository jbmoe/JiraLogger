package com.example.jiralogger.presentation.issues.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.jiralogger.R
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.presentation.components.IconPablo
import com.example.jiralogger.presentation.components.PabloText
import com.example.jiralogger.presentation.util.ImageFromUrl

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IssueItem(issue: Issue, onItemClicked: (Issue) -> Unit) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 8.dp,
        shadowElevation = 2.dp,
    ) {
        ListItem(
            modifier = Modifier
                .clickable { onItemClicked(issue) },
            icon = {
                ImageFromUrl(
                    url = issue.project.avatarUrls.x48,
                    placeholder = R.drawable.default_project_avatar,
                    contentDescription = "Project Image",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))
                        .background(Color.White)
                )
            },
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PabloText(
                        text = issue.key,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.padding(4.dp))
                    ImageFromUrl(
                        url = issue.priority.iconUrl,
                        placeholder = R.drawable.priority_medium,
                        modifier = Modifier.size(18.dp)
                    )
                }
            },
            secondaryText = {
                PabloText(
                    text = issue.summary,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis
                )
            },
            trailing = {
                IconPablo(
                    resourceId = R.drawable.ic_baseline_navigate_next_24,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        )
    }
}