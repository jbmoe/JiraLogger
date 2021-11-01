package com.example.jiralogger.presentation.util.preview_paramater

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.presentation.work_log_detail.WorkLogDetailState

class WorkLogDetailPreviewParameterProvider : PreviewParameterProvider<WorkLogDetailState> {
    override val values: Sequence<WorkLogDetailState>
        get() = sequenceOf(
            WorkLogDetailState(
                item = TestData.WORK_LOG_TEST_DATA[0],
                isEditing = true
            )
        )
}