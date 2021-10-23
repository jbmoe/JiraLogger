package com.example.jiralogger.presentation.preview_paramater

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jiralogger.common.constant.TestData
import com.example.jiralogger.domain.model.Issue

class IssueListItemPreviewParameterProvider : PreviewParameterProvider<Issue> {
    override val values: Sequence<Issue>
        get() = sequenceOf(TestData.API_RESULT_TEST_OBJECT.toIssuesList().random())
}