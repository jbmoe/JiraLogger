package com.example.jiralogger.presentation.issue_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.common.Resource
import com.example.jiralogger.domain.use_case.get_issue.GetIssueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class IssueDetailViewModel @Inject constructor(
    private val getIssueUseCase: GetIssueUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(IssueDetailState())
    val state: State<IssueDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_ISSUE_KEY)?.let { issueKey ->
            getIssue(issueKey)
        }
    }

    private fun getIssue(issueKey: String) {
        getIssueUseCase(issueKey).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = IssueDetailState(
                        issue = result.data
                    )
                }
                is Resource.Error -> {
                    _state.value =
                        IssueDetailState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = IssueDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}