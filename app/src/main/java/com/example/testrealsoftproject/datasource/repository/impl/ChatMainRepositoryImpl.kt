package com.example.testrealsoftproject.datasource.repository.impl

import com.example.testrealsoftproject.datasource.repository.IChatMainRepository
import com.example.testrealsoftproject.model.ChatUsersData

class ChatMainRepositoryImpl : IChatMainRepository {
    private val data = mutableListOf<ChatUsersData>()
    override suspend fun getChatUsers(): List<ChatUsersData> {
        val chatUser = ChatUsersData(
            "",
            "Bexruzbek",
            true,
            "00:15",
            "Consectetur adipiscing elit duis tristique sollicitudin nib",
            null,
            true
        )
        val chatUser1 = ChatUsersData(
            "", "Javlonbek", true, "08:15", "tristique sollicitudin nibh sit", 12
        )
        val chatUser2 = ChatUsersData(
            "",
            "Sardor",
            false,
            "16:15",
            "Consectetur adipiscing elit duis tristique sollicitudin nib",
            122,
            false
        )
        val chatUser3 = ChatUsersData(
            "",
            "Shohruz",
            true,
            "00:45",
            "tristique sollicitudin nibh sit",
            8,
            false
        )
        val chatUser4 = ChatUsersData(
            "",
            "Sarvar",
            true,
            "03:15",
            "Consectetur adipiscing elit duis tristique sollicitudin nib",
            34,
            true
        )


        data.add(chatUser)
        data.add(chatUser1)
        data.add(chatUser2)
        data.add(chatUser3)
        data.add(chatUser4)

        return data
    }
}