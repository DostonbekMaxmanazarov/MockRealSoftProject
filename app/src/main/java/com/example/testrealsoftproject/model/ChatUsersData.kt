package com.example.testrealsoftproject.model

import java.io.Serializable

data class ChatUsersData(
    val image: String? = null,
    val name: String? = null,
    val isYou: Boolean = false,
    val time: String? = null,
    val message: String? = null,
    val messageCount: Int? = null,
    val isYouSend: Boolean = false
) : Serializable
