package com.example.testrealsoftproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testrealsoftproject.datasource.repository.IWorkPlanRepository
import com.example.testrealsoftproject.model.WorkPlanData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkPlanViewModel @Inject constructor(
    private var repo: IWorkPlanRepository
) : ViewModel() {

    private val _taskStateFlow = MutableStateFlow<List<WorkPlanData>>(mutableListOf())
    val taskStateFlow: StateFlow<List<WorkPlanData>> get() = _taskStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _taskStateFlow.value = repo.getTasks()
        }
    }
}