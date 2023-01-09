package com.example.testrealsoftproject.di

import com.example.testrealsoftproject.datasource.repository.*
import com.example.testrealsoftproject.datasource.repository.impl.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun getChatRepository(): IChatRepository {
        return ChatRepositoryImpl()
    }

    @Singleton
    @Provides
    fun getChatMainRepository(): IChatMainRepository {
        return ChatMainRepositoryImpl()
    }

    @Singleton
    @Provides
    fun getWorkPlanRepository(): IWorkPlanRepository {
        return WorkPlanRepositoryImpl()
    }

    @Singleton
    @Provides
    fun getTodayRepository(): ITodayRepository {
        return TodayRepositoryImpl()
    }

    @Singleton
    @Provides
    fun getAuthRepository(): IAuthRepository {
        return AuthRepositoryImpl()
    }
}