package com.example.testrealsoftproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testrealsoftproject.datasource.network.response.TaskItemsResponse
import com.example.testrealsoftproject.datasource.network.response.TaskResponse
import com.example.testrealsoftproject.datasource.repository.ITodayRepository
import com.example.testrealsoftproject.datasource.repository.impl.TodayRepositoryImpl
import com.example.testrealsoftproject.presentation.use_case.ISearchTodayTasks
import com.example.testrealsoftproject.presentation.use_case.impl.SearchTodayTasksImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(private var repo: ITodayRepository) : ViewModel() {

    private val _taskStateFlow = MutableStateFlow<List<TaskResponse>>(mutableListOf())
    val taskStateFlow: StateFlow<List<TaskResponse>> get() = _taskStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _taskStateFlow.value = repo.getTasks()
        }
    }
}