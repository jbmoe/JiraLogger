package com.example.jiralogger.presentation.util.preview_paramater

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.presentation.issues.IssuesState

class IssueListPreviewParameterProvider : PreviewParameterProvider<IssuesState> {
    override val values: Sequence<IssuesState>
        get() = sequenceOf(IssuesState(items = TestData.API_RESULT_TEST_OBJECT.toIssuesList()))
}