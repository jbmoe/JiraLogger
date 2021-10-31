package com.example.jiralogger.presentation.issue_list.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.jiralogger.R
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.presentation.issue_detail.IssueDetailState
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.ImageFromUrl
import com.example.jiralogger.presentation.util.preview_paramater.IssueDetailPreviewParameterProvider

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IssueListItem(issue: Issue, onItemClicked: (Issue) -> Unit) {
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
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Text(
                    text = issue.key,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.padding(4.dp))
                ImageFromUrl(
                    url = issue.priority.iconUrl,
                    placeholder = R.drawable.priority_medium,
                    contentDescription = "Priority Icon",
                    modifier = Modifier.size(18.dp)
                )
            }
        },
        secondaryText = {
            Text(
                text = issue.summary,
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                overflow = TextOverflow.Ellipsis
            )
        },
        trailing = {
            Icon(
                painter = painterResource(R.drawable.ic_baseline_navigate_next_24),
                contentDescription = "Continue",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    )
}

@Preview(name = "Light mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(IssueDetailPreviewParameterProvider::class) state: IssueDetailState) {
    JiraLoggerTheme {
        IssueListItem(
            issue = state.item as Issue,
            onItemClicked = {}
        )
    }
}