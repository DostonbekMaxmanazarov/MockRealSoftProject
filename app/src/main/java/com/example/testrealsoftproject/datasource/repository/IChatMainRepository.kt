package com.example.testrealsoftproject.datasource.repository

import com.example.testrealsoftproject.model.ChatUsersData

interface IChatMainRepository {
    suspend fun getChatUsers():List<ChatUsersData>
}