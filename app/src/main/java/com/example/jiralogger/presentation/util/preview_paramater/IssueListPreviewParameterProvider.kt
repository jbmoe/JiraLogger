package com.example.jiralogger.presentation.util.preview_paramater

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.presentation.issue_list.IssueListState

class IssueListPreviewParameterProvider : PreviewParameterProvider<IssueListState> {
    override val values: Sequence<IssueListState>
        get() = sequenceOf(
            IssueListState(
                itemMap = TestData.API_RESULT_TEST_OBJECT.toIssuesList().groupBy {
                    it.status.name == "Closed"
                })
        )
}