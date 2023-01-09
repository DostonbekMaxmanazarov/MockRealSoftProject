package com.example.testrealsoftproject.datasource.repository.impl

import com.example.testrealsoftproject.datasource.repository.IAuthRepository
import com.example.testrealsoftproject.model.ResultData
import com.example.testrealsoftproject.model.UserAccount
import kotlin.math.log

class AuthRepositoryImpl : IAuthRepository {
    private val login = "Admin"
    private val password = "12345"

    override suspend fun checkAccount(account: UserAccount): ResultData<Boolean> {
        return if (account.login == login && account.password == password) {
            ResultData.Success(true)
        } else {
            ResultData.Failure("Неверный логин или пароль")
        }
    }
}