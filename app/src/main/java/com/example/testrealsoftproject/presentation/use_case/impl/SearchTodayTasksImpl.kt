package com.example.testrealsoftproject.presentation.use_case.impl

import com.example.testrealsoftproject.datasource.network.response.TaskItemsResponse
import com.example.testrealsoftproject.presentation.use_case.ISearchTodayTasks

class SearchTodayTasksImpl : ISearchTodayTasks {

    override suspend fun searchNotesExecute(
        notes: List<TaskItemsResponse>, query: String
    ): List<TaskItemsResponse> {
        if (query.isBlank()) return notes

        return notes
            .filter { it.name != null }
            .filter {
                it.name!!.replace(" ", "").lowercase().contains(query.replace(" ", "").lowercase())
            }
    }
}