package com.example.jiralogger.presentation.util.preview_paramater

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.presentation.work_logs.WorkLogsState
import java.text.SimpleDateFormat

class WorkLogListPreviewParameterProvider : PreviewParameterProvider<WorkLogsState> {
    override val values: Sequence<WorkLogsState>
        get() = sequenceOf(WorkLogsState(itemMap = TestData.WORK_LOG_TEST_DATA.groupBy {
            val sdf = SimpleDateFormat("E d. MMMM yy")
            sdf.format(it.dateWorked)
        }))
}