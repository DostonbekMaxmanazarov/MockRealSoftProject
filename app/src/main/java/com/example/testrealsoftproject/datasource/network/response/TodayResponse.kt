package com.example.testrealsoftproject.datasource.network.response

import java.io.Serializable

data class TaskResponse(
    var data: List<TaskItemsResponse>? = null,
    val title: String? = null
) : Serializable

data class TaskItemsResponse(
    val image: String? = null,
    val name: String? = null,
    val numberCode: String? = null,
    val date: String? = null,
    val description: String? = null
) : Serializable