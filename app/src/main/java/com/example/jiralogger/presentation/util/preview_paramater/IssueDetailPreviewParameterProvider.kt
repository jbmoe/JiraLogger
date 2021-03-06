package com.example.jiralogger.presentation.util.preview_paramater

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.presentation.issue_detail.IssueDetailState

class IssueDetailPreviewParameterProvider : PreviewParameterProvider<IssueDetailState> {
    override val values: Sequence<IssueDetailState>
        get() = sequenceOf(
            IssueDetailState(
                item = TestData.API_RESULT_TEST_OBJECT.toIssuesList().find { it.key == "ESBJ-233" }
            )
        )
}