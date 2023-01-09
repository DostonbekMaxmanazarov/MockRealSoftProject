package com.example.testrealsoftproject.datasource.repository.impl

import com.example.testrealsoftproject.datasource.repository.IChatRepository
import com.example.testrealsoftproject.model.BaseChatData

class ChatRepositoryImpl : IChatRepository {

    private val leftPhotos = mutableListOf<BaseChatData.LeftPhotoData>()
    private val leftMessages = mutableListOf<BaseChatData.LeftMessageData>()
    private val leftFiles = mutableListOf<BaseChatData.LeftFileData>()
    private val rightMessages = mutableListOf<BaseChatData.RightMessageData>()
    private val rightVoice = mutableListOf<BaseChatData.RightVoiceData>()

    override suspend fun getLeftPhotos(): List<BaseChatData.LeftPhotoData> {
        val data = BaseChatData.LeftPhotoData("im_photos_home", "01:34")
        leftPhotos.add(data)
        return leftPhotos
    }

    override suspend fun getLeftMessage(): List<BaseChatData.LeftMessageData> {
        val data = BaseChatData.LeftMessageData(
            "Velit officia consequat duis enim velit mollit. Exercitation veniam consequat sunt nostrud."
        )
        leftMessages.add(data)
        return leftMessages
    }

    override suspend fun getLeftFile(): List<BaseChatData.LeftFileData> {
        val data = BaseChatData.LeftFileData("file_url")
        leftFiles.add(data)
        return leftFiles
    }

    override suspend fun getRightMessage(): List<BaseChatData.RightMessageData> {
        val data = BaseChatData.RightMessageData("Exercitation veniam consequat sunt nostrud amet")
        rightMessages.add(data)
        return rightMessages
    }

    override suspend fun getRightVoice(): List<BaseChatData.RightVoiceData> {
        val data = BaseChatData.RightVoiceData("voice url")
        rightVoice.add(data)
        return rightVoice
    }

}