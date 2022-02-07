package com.example.jiralogger.presentation.util.preview_paramater

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.presentation.issue.IssueState

class IssueDetailPreviewParameterProvider : PreviewParameterProvider<IssueState> {
    override val values: Sequence<IssueState>
        get() = sequenceOf(
            IssueState(
                item = TestData.API_RESULT_TEST_OBJECT.toIssuesList().find { it.key == "ESBJ-233" }
            )
        )
}