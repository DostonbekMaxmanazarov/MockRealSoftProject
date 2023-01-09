package com.example.testrealsoftproject.model

sealed class BaseChatData {

    data class LeftPhotoData(
        val photo: String, val time: String
    ) : BaseChatData()

    data class LeftMessageData(
        val message: String
    ) : BaseChatData()

    data class RightMessageData(
        val message: String
    ) : BaseChatData()

    data class RightVoiceData(
        val message: String
    ) : BaseChatData()

    data class LeftVoiceData(
        val message: String
    ) : BaseChatData()

    data class LeftFileData(
        val message: String
    ) : BaseChatData()

    data class RightFileData(
        val message: String
    ) : BaseChatData()

    data class DayData(
        val day: String
    ) : BaseChatData()
}