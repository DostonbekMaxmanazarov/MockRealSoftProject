package com.example.testrealsoftproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testrealsoftproject.datasource.repository.IAuthRepository
import com.example.testrealsoftproject.datasource.repository.IChatRepository
import com.example.testrealsoftproject.datasource.repository.impl.ChatRepositoryImpl
import com.example.testrealsoftproject.model.BaseChatData
import com.example.testrealsoftproject.model.ResultData
import com.example.testrealsoftproject.model.UserAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: IAuthRepository
) : ViewModel() {

    private var _successFlow = MutableSharedFlow<ResultData<Boolean>>()
    val successFlow: SharedFlow<ResultData<Boolean>> get() = _successFlow.asSharedFlow()

    fun check(account: UserAccount) {
        viewModelScope.launch {
            val value = repo.checkAccount(account)
            _successFlow.emit(value)
        }
    }
}