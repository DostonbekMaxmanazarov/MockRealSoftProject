package com.example.testrealsoftproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testrealsoftproject.datasource.network.response.TaskItemsResponse
import com.example.testrealsoftproject.datasource.repository.IChatMainRepository
import com.example.testrealsoftproject.datasource.repository.impl.ChatMainRepositoryImpl
import com.example.testrealsoftproject.model.ChatUsersData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatMainViewModel @Inject constructor(
    private val repo: IChatMainRepository
) : ViewModel() {

    private val _chatUsers = MutableStateFlow<List<ChatUsersData>>(mutableListOf())
    val chatUsers: StateFlow<List<ChatUsersData>> get() = _chatUsers.asStateFlow()

    init {
        viewModelScope.launch {
            _chatUsers.value = repo.getChatUsers()
        }
    }
}