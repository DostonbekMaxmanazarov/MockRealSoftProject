package com.example.testrealsoftproject.datasource.repository

import com.example.testrealsoftproject.model.BaseChatData

interface IChatRepository {
    suspend fun getLeftPhotos(): List<BaseChatData.LeftPhotoData>
    suspend fun getLeftMessage(): List<BaseChatData.LeftMessageData>
    suspend fun getLeftFile(): List<BaseChatData.LeftFileData>
    suspend fun getRightMessage(): List<BaseChatData.RightMessageData>
    suspend fun getRightVoice(): List<BaseChatData.RightVoiceData>
}