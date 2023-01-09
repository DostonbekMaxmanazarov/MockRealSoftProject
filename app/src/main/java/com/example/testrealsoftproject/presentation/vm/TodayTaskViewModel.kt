package com.example.testrealsoftproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testrealsoftproject.datasource.network.response.TaskItemsResponse
import com.example.testrealsoftproject.presentation.use_case.ISearchTodayTasks
import com.example.testrealsoftproject.presentation.use_case.impl.SearchTodayTasksImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodayTaskViewModel @Inject constructor(
    private var useCase: ISearchTodayTasks
) : ViewModel() {

    private val _searchTaskStateFlow = MutableSharedFlow<List<TaskItemsResponse>>()
    val searchTaskStateFlow: SharedFlow<List<TaskItemsResponse>> get() = _searchTaskStateFlow.asSharedFlow()

    fun searchTasks(tasks: List<TaskItemsResponse>, searchText: String) {
        viewModelScope.launch {
            _searchTaskStateFlow.emit(useCase.searchNotesExecute(tasks, searchText))
        }
    }
}