package com.example.jiralogger.presentation.issue

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.common.Resource
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.domain.use_case.issue.GetIssue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class IssueViewModel @Inject constructor(
    private val getIssueUseCase: GetIssue,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(IssueState())
    val state: State<IssueState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_ISSUE_KEY)?.let { issueKey ->
            getIssue(issueKey)
        }
    }

    private fun getIssue(issueKey: String) {
        getIssueUseCase(issueKey).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = IssueState(
                        item = result.data
                    )
                }
                is Resource.Error -> {
                    _state.value =
                        IssueState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = IssueState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}