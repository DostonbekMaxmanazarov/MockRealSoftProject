package com.example.testrealsoftproject.model

sealed class ResultData<out T> {
    data class Success<out T>(
        val value: T
    ) : ResultData<T>()

    data class Failure(
        val strError: String
    ) : ResultData<Nothing>()
}