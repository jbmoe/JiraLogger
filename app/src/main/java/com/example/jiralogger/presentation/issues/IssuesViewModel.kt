package com.example.jiralogger.presentation.issues

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiralogger.common.Resource
import com.example.jiralogger.domain.use_case.issue.GetFilteredIssues
import com.example.jiralogger.domain.util.IssueFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(
    private val getIssues: GetFilteredIssues
) : ViewModel() {
    private val _state = mutableStateOf(IssuesState())
    val state: State<IssuesState> = _state

    val issueFilters: List<IssueFilter> = listOf(
        IssueFilter.Assigned,
        IssueFilter.Seen,
        IssueFilter.Watching,
        IssueFilter.EV
    )

    private var _refreshAction: (() -> Unit)? = null

    init {
        getFilteredIssues(_state.value.issueFilter)
    }

    fun onEvent(event: IssuesEvent) {
        when (event) {
            is IssuesEvent.OnFilterChanged -> {
                if (state.value.issueFilter::class == event.filter::class) return
                getFilteredIssues(event.filter)
            }
            is IssuesEvent.OnSearch -> {
                if (state.value.issueFilter::class == event.filter::class) return
                getFilteredIssues(event.filter)

            }
            IssuesEvent.OnRefresh -> _refreshAction?.invoke()
        }
    }

    private fun getFilteredIssues(filter: IssueFilter, ignoreCache: Boolean = false) {
        getIssues(filter, ignoreCache).onEach { result ->
            val filter = if (filter is IssueFilter.SEARCH) _state.value.issueFilter else filter
            _state.value = when (result) {
                is Resource.Success -> {
                    IssuesState(
                        items = result.data ?: emptyList(),
                        issueFilter = filter
                    )
                }
                is Resource.Error -> {
                    IssuesState(
                        isError = true,
                        error = result.message ?: "An unexpected error occurred",
                        issueFilter = filter
                    )
                }
                is Resource.Loading -> {
                    IssuesState(
                        isLoading = true,
                        issueFilter = filter
                    )
                }
            }
        }.launchIn(viewModelScope)
        _refreshAction = { getFilteredIssues(filter, true) }
    }
}