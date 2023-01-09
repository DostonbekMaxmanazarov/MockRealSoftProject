package com.example.testrealsoftproject.datasource.repository

import com.example.testrealsoftproject.datasource.network.response.TaskResponse

interface ITodayRepository {
    suspend fun getTasks(): List<TaskResponse>
}