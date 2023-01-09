package com.example.testrealsoftproject.datasource.repository

import com.example.testrealsoftproject.model.ResultData
import com.example.testrealsoftproject.model.UserAccount

interface IAuthRepository {
    suspend fun checkAccount(account: UserAccount): ResultData<Boolean>
}