package com.example.jiralogger.presentation.work_log_detail

import androidx.lifecycle.ViewModel
import com.example.jiralogger.domain.use_case.work_log.GetWorkLog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkLogDetailViewModel @Inject constructor(
    private val getWorkLog: GetWorkLog
) : ViewModel() {

}
