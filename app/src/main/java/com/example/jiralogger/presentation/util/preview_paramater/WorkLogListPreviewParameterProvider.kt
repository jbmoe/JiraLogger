package com.example.jiralogger.presentation.util.preview_paramater

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jiralogger.common.test_data.TestData
import com.example.jiralogger.presentation.work_log_list.WorkLogListState
import java.text.SimpleDateFormat

class WorkLogListPreviewParameterProvider : PreviewParameterProvider<WorkLogListState> {
    override val values: Sequence<WorkLogListState>
        get() = sequenceOf(WorkLogListState(itemMap = TestData.WORK_LOG_TEST_DATA.groupBy {
            val sdf = SimpleDateFormat("E d. MMMM yy")
            sdf.format(it.dateWorked)
        }))
}