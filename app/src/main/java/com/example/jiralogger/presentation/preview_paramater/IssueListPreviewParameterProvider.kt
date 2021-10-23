package com.example.jiralogger.presentation.preview_paramater

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jiralogger.common.constant.TestData
import com.example.jiralogger.presentation.issue_list.IssueListState

class IssueListPreviewParameterProvider : PreviewParameterProvider<IssueListState> {
    override val values: Sequence<IssueListState>
        get() = sequenceOf(IssueListState(issues = TestData.API_RESULT_TEST_OBJECT.toIssuesList().shuffled()))
}