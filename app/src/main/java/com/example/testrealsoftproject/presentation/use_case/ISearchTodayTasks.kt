package com.example.testrealsoftproject.presentation.use_case

import com.example.testrealsoftproject.datasource.network.response.TaskItemsResponse

interface ISearchTodayTasks {
    suspend fun searchNotesExecute(
        notes: List<TaskItemsResponse>, query: String
    ): List<TaskItemsResponse>
}