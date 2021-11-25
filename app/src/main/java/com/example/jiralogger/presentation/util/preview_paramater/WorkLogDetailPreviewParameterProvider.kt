package com.example.jiralogger.presentation.util.preview_paramater

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jiralogger.presentation.work_log_add_edit.AddEditState

class WorkLogDetailPreviewParameterProvider : PreviewParameterProvider<AddEditState> {
    override val values: Sequence<AddEditState>
        get() = sequenceOf(AddEditState())
}