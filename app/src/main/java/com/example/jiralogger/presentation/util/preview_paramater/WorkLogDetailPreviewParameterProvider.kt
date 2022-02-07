package com.example.jiralogger.presentation.util.preview_paramater

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jiralogger.presentation.work_log.WorkLogState

class WorkLogDetailPreviewParameterProvider : PreviewParameterProvider<WorkLogState> {
    override val values: Sequence<WorkLogState>
        get() = sequenceOf(WorkLogState())
}