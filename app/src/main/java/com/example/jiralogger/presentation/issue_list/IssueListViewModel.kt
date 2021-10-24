package com.example.jiralogger.presentation.issue_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.common.Resource
import com.example.jiralogger.common.constant.Filters
import com.example.jiralogger.domain.use_case.get_filtered_issues.GetFilteredIssuesUseCase
import com.example.jiralogger.domain.use_case.get_issue.GetIssueUseCase
import com.example.jiralogger.domain.use_case.get_issues.GetIssuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class IssueListViewModel @Inject constructor(
    private val getIssuesUseCase: GetIssuesUseCase,
    private val getIssueUseCase: GetIssueUseCase,
    private val getIssuesByFilter: GetFilteredIssuesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(IssueListState())
    val state: State<IssueListState> = _state

    private var _refreshAction: () -> (Unit) = {}

    init {
        getFilteredIssues(Filters.ASSIGNED_TO_ME)
    }

    fun refresh() {
        _refreshAction()
    }

    fun getAssignedToMe() {
        getFilteredIssues(Filters.ASSIGNED_TO_ME)
        _refreshAction = { getAssignedToMe() }
    }

    fun getLastSeen() {
        getFilteredIssues(Filters.LAST_SEEN)
        _refreshAction = { getLastSeen() }
    }

    fun getFavourites() {
        getFilteredIssues()
        _refreshAction = { getFavourites() }
    }

    private fun getFilteredIssues(filter: String? = null) {
        getIssuesByFilter(filter).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = IssueListState(
                        issues = result.data ?: emptyList()
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
        _refreshAction = { getFilteredIssues(filter) }
    }

    fun getIssueByKey(issueKey: String) {
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