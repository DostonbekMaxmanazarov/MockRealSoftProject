package com.example.testrealsoftproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testrealsoftproject.datasource.repository.IChatRepository
import com.example.testrealsoftproject.datasource.repository.impl.ChatRepositoryImpl
import com.example.testrealsoftproject.model.BaseChatData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repo: IChatRepository
) : ViewModel() {

    private val _chatUsers = MutableStateFlow<List<BaseChatData>>(mutableListOf())
    val chatUsers: StateFlow<List<BaseChatData>> get() = _chatUsers.asStateFlow()

    init {
        getChats()
    }

    private fun getChats() = viewModelScope.launch {
        val leftFileDeferred = async { repo.getLeftFile() }
        val rightVoiceDeferred = async { repo.getRightVoice() }
        val leftPhotosDeferred = async { repo.getLeftPhotos() }
        val leftMessageDeferred = async { repo.getLeftMessage() }
        val rightMessageDeferred = async { repo.getRightMessage() }

        val chatsItem = mutableListOf<BaseChatData>()
        chatsItem.addAll(leftPhotosDeferred.await())
        chatsItem.addAll(leftFileDeferred.await())
        chatsItem.addAll(rightVoiceDeferred.await())
        chatsItem.add(BaseChatData.DayData("Today"))
        chatsItem.addAll(leftMessageDeferred.await())
        chatsItem.addAll(rightMessageDeferred.await())

        _chatUsers.value = chatsItem
    }
}