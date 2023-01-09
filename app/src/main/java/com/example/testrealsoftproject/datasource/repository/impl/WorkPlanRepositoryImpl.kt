package com.example.testrealsoftproject.datasource.repository.impl

import com.example.testrealsoftproject.datasource.repository.IWorkPlanRepository
import com.example.testrealsoftproject.model.WorkPlanData

class WorkPlanRepositoryImpl : IWorkPlanRepository {

    private val data = mutableListOf<WorkPlanData>()

    override suspend fun getTasks(): List<WorkPlanData> {
        val item = WorkPlanData(
            "Qurilish vazirligi bilan uchrashuv tashkil etish tashkil etish tashkilasd", false
        )
        val item1 = WorkPlanData("Tijorat taklifi loyihasi va prezentasiya tayorlash", false)
        val item2 = WorkPlanData(null, true)

        data.add(item)
        data.add(item1)
        data.add(item2)

        return data
    }

}