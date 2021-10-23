package com.example.jiralogger.presentation.issue_list.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiralogger.R
import com.example.jiralogger.common.constant.TestData
import com.example.jiralogger.domain.model.Issue
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme

@Composable
fun IssueListItem(issue: Issue, onItemClicked: (Issue) -> Unit) {
    Row(modifier = Modifier
        .padding(8.dp)
        .clickable { onItemClicked(issue) }
        .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(
                id = issue.projectImage ?: R.drawable.default_project_avatar
            ),
            contentDescription = "Project Image",
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, MaterialTheme.colors.secondary, RoundedCornerShape(10.dp))
        )
        Spacer(Modifier.width(8.dp))
        ColumnTexts(issue)
    }

}

@Composable
private fun ColumnTexts(issue: Issue) {
    var isExpanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = "${issue.projectName} / ${issue.key}",
            color = MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.subtitle2,
        )
        Spacer(Modifier.padding(4.dp))
        Text(
            text = "${issue.summary}",
            modifier = Modifier
                .padding(4.dp),//.clickable { isExpanded = !isExpanded },
            maxLines = 1, //if (isExpanded) Int.MAX_VALUE else 1,
            style = MaterialTheme.typography.body2
        )
    }
}


@Preview(name = "Light mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview() {
    JiraLoggerTheme {
        IssueListItem(
            issue = TestData.ISSUE_TEST_OBJECT,
            onItemClicked = {

            }
        )
    }
}