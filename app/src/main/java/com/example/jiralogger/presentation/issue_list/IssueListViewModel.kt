package com.example.jiralogger.presentation.issue_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.common.Resource
import com.example.jiralogger.domain.use_case.issue.GetFilteredIssues
import com.example.jiralogger.domain.use_case.issue.GetIssue
import com.example.jiralogger.domain.util.IssueFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class IssueListViewModel @Inject constructor(
    private val getIssue: GetIssue,
    private val getIssuesByFilter: GetFilteredIssues
) : ViewModel() {
    private val _state = mutableStateOf(IssueListState())
    val state: State<IssueListState> = _state

    lateinit var filters: List<IssueFilter>

    private var _refreshAction: (() -> Unit)? = null

    init {
        initFilters()
        getFilteredIssues(IssueFilter.A_Assigned)
    }

    private fun initFilters() {
        filters = listOf(
            IssueFilter.A_Assigned,
            IssueFilter.B_Seen,
            IssueFilter.C_WATCHING,
            IssueFilter.D_EV
        )
    }

    fun onEvent(event: IssuesEvent) {
        when (event) {
            is IssuesEvent.Filter -> {
                if (state.value.issueFilter::class == event.filter::class) return
                getFilteredIssues(event.filter)
            }
            is IssuesEvent.Refresh -> refresh()
            is IssuesEvent.ToggleFilterVisibility -> {
                _state.value = _state.value.copy(
                    filterIsVisible = !_state.value.filterIsVisible
                )
            }
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
                        items = result.data?.sortedBy { it.status.name } ?: emptyList(),
                        issueFilter = issueFilter,
                        filterIsVisible = _state.value.filterIsVisible
                    )
                }
                is Resource.Error -> {
                    _state.value =
                        IssueListState(
                            error = result.message ?: "An unexpected error occurred",
                            issueFilter = issueFilter,
                            filterIsVisible = _state.value.filterIsVisible
                        )
                }
                is Resource.Loading -> {
                    _state.value = IssueListState(
                        isLoading = true,
                        issueFilter = issueFilter,
                        filterIsVisible = _state.value.filterIsVisible
                    )
                }
            }
        }.launchIn(viewModelScope)
        _refreshAction = { getFilteredIssues(issueFilter) }
    }

    private fun getIssueByKey(issueKey: String) {
        getIssue(issueKey).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = IssueListState(
                        items = if (result.data != null) listOf(result.data) else emptyList()
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