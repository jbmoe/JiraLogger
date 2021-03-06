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
        getFilteredIssues(IssueFilter.Assigned)
    }

    private fun initFilters() {
        filters = listOf(
            IssueFilter.Assigned,
            IssueFilter.Seen,
            IssueFilter.WATCHING,
            IssueFilter.EV
        )
    }

    fun onEvent(event: IssuesEvent) {
        when (event) {
            is IssuesEvent.Filter -> {
                if (state.value.issueFilter::class == event.filter::class) return
                getFilteredIssues(event.filter)
            }
            is IssuesEvent.Search -> {
                getFilteredIssues(event.filter)
            }
            is IssuesEvent.Refresh -> refresh()
            is IssuesEvent.ToggleFilterVisibility -> {
                _state.value = _state.value.copy(
                    filterIsVisible = !_state.value.filterIsVisible,
                    searchIsVisible = false
                )
                if (_state.value.filterIsVisible)
                    getFilteredIssues(_state.value.issueFilter)
            }
            is IssuesEvent.ToggleSearchVisibility -> {
                _state.value = _state.value.copy(
                    filterIsVisible = false,
                    searchIsVisible = !_state.value.searchIsVisible
                )
            }
        }
    }

    private fun refresh() {
        _refreshAction?.invoke()
    }

    private fun getFilteredIssues(issueFilter: IssueFilter, ignoreCache: Boolean = false) {
        getIssuesByFilter(issueFilter, ignoreCache).onEach { result ->
            val filter =
                if (issueFilter !is IssueFilter.SEARCH) issueFilter else IssueFilter.Assigned
            when (result) {
                is Resource.Success -> {
                    _state.value = IssueListState(
                        items = result.data?.sortedBy { it.status.name } ?: emptyList(),
                        issueFilter = filter,
                        filterIsVisible = _state.value.filterIsVisible,
                        searchIsVisible = _state.value.searchIsVisible
                    )
                }
                is Resource.Error -> {
                    _state.value =
                        IssueListState(
                            error = result.message ?: "An unexpected error occurred",
                            issueFilter = filter,
                            filterIsVisible = _state.value.filterIsVisible,
                            searchIsVisible = _state.value.searchIsVisible
                        )
                }
                is Resource.Loading -> {
                    _state.value = IssueListState(
                        isLoading = true,
                        issueFilter = filter,
                        filterIsVisible = _state.value.filterIsVisible,
                        searchIsVisible = _state.value.searchIsVisible
                    )
                }
            }
        }.launchIn(viewModelScope)
        _refreshAction = { getFilteredIssues(issueFilter, true) }
    }
}