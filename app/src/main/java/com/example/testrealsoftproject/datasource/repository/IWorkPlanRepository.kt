package com.example.testrealsoftproject.datasource.repository

import com.example.testrealsoftproject.model.WorkPlanData

interface IWorkPlanRepository {
    suspend fun getTasks():List<WorkPlanData>
}