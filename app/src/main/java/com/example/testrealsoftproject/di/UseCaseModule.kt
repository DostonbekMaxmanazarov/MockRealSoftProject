package com.example.testrealsoftproject.di

import com.example.testrealsoftproject.presentation.use_case.ISearchTodayTasks
import com.example.testrealsoftproject.presentation.use_case.impl.SearchTodayTasksImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun getSearchToday(): ISearchTodayTasks {
        return SearchTodayTasksImpl()
    }

}