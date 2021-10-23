package com.example.jiralogger.presentation.preview_paramater

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jiralogger.common.constant.TestData
import com.example.jiralogger.presentation.issue_detail.IssueDetailState

class IssueDetailPreviewParameterProvider : PreviewParameterProvider<IssueDetailState> {
    override val values: Sequence<IssueDetailState>
        get() = sequenceOf(
            IssueDetailState(
                issue = TestData.API_RESULT_TEST_OBJECT.toIssuesList().random()
            )
        )
}