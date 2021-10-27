package com.example.jiralogger.presentation.issue_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.common.Resource
import com.example.jiralogger.domain.use_case.issue.GetFilteredIssues
import com.example.jiralogger.domain.use_case.issue.GetIssueUseCase
import com.example.jiralogger.domain.util.IssueFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class IssueListViewModel @Inject constructor(
    private val getIssueUseCase: GetIssueUseCase,
    private val getIssuesByFilter: GetFilteredIssues
) : ViewModel() {
    private val _state = mutableStateOf(IssueListState())
    val state: State<IssueListState> = _state

    private var _refreshAction: (() -> Unit)? = null

    init {
        getFilteredIssues(IssueFilter.Assigned)
    }

    fun onEvent(event: IssuesEvent) {
        when (event) {
            is IssuesEvent.Filter -> {
                if (state.value.issueFilter::class == event.filter::class) return
                getFilteredIssues(event.filter)
            }
            is IssuesEvent.Refresh -> refresh()
        }
    }

    private fun refresh() {
        _refreshAction?.invoke()
    }

    private fun getFilteredIssues(issueFilter: IssueFilter) {
        getIssuesByFilter(issueFilter).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = IssueListState(
                        issues = result.data ?: emptyList(),
                        issueFilter = issueFilter
                    )
                }
                is Resource.Error -> {
                    _state.value =
                        IssueListState(
                            error = result.message ?: "An unexpected error occurred",
                            issueFilter = issueFilter
                        )
                }
                is Resource.Loading -> {
                    _state.value = IssueListState(isLoading = true, issueFilter = issueFilter)
                }
            }
        }.launchIn(viewModelScope)
        _refreshAction = { getFilteredIssues(issueFilter) }
    }

    private fun getIssueByKey(issueKey: String) {
        getIssueUseCase(issueKey).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = IssueListState(
                        issues = listOf(result.data)
                    )
                }
                is Resource.Error -> {
                    _state.value =
                        IssueListState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = IssueListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
        _refreshAction = { getIssueByKey(issueKey) }
    }
}