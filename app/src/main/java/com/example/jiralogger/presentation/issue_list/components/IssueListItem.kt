package com.example.jiralogger.presentation.issue_list.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.jiralogger.R
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.ImageFromUrl
import com.example.jiralogger.presentation.util.preview_paramater.IssueListItemPreviewParameterProvider

@Composable
fun IssueListItem(issue: Issue, onItemClicked: (Issue) -> Unit) {
    Row(modifier = Modifier
        .clickable { onItemClicked(issue) }
        .padding(8.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageFromUrl(
            url = issue.project.avatarUrls.x48,
            placeholder = R.drawable.default_project_avatar,
            contentDescription = "Project Image",
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, MaterialTheme.colors.secondary, RoundedCornerShape(10.dp))
                .background(Color.White)
        )
        Spacer(Modifier.width(4.dp))
        ColumnTexts(issue)
    }

}

@Composable
private fun ColumnTexts(issue: Issue) {
    Column(Modifier.padding(4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = issue.key,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.h4
            )
            Spacer(Modifier.padding(4.dp))
            ImageFromUrl(
                url = issue.priority.iconUrl,
                placeholder = R.drawable.priority_medium,
                contentDescription = "Priority Icon",
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(Modifier.padding(2.dp))
        Text(
            text = issue.summary,
            maxLines = 1,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
        )
    }
}

@Preview(name = "Light mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview(@PreviewParameter(IssueListItemPreviewParameterProvider::class) issue: Issue) {
    JiraLoggerTheme {
        IssueListItem(
            issue = issue,
            onItemClicked = {

            }
        )
    }
}