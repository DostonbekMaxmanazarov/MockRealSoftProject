package com.example.testrealsoftproject.datasource.repository.impl

import com.example.testrealsoftproject.datasource.network.response.TaskItemsResponse
import com.example.testrealsoftproject.datasource.network.response.TaskResponse
import com.example.testrealsoftproject.datasource.repository.ITodayRepository

class TodayRepositoryImpl : ITodayRepository {

    private var mList = mutableListOf<TaskResponse>()

    override suspend fun getTasks(): List<TaskResponse> {
        val items = mutableListOf<TaskItemsResponse>()
        val items1 = mutableListOf<TaskItemsResponse>()
        val items2 = mutableListOf<TaskItemsResponse>()
        val items3 = mutableListOf<TaskItemsResponse>()

        val item = TaskItemsResponse(
            "im_person",
            "Xasanov B.",
            "EDO-096",
            "15 may, 2020",
            "Consectetur adipiscing elit duis tristique sollicitudin nibh commodo…"
        )
        val item1 = TaskItemsResponse(
            "im_person",
            "Salohiddinov D.",
            "ABE-098",
            "15 may, 2021",
            "Consectetur adipiscing elit duis tristique sollicitudin nibh commodo…"
        )
        val item2 = TaskItemsResponse(
            "im_person",
            "Davlatov A.",
            "ABE-098",
            "15 may, 2021",
            "Consectetur adipiscing elit duis tristique sollicitudin nibh commodo…"
        )
        val item3 = TaskItemsResponse(
            "im_person",
            "Bahodirov H.",
            "ABE-098",
            "15 may, 2021",
            "Consectetur adipiscing elit duis tristique sollicitudin nibh commodo…"
        )

        for (i in 0..5) {
            items.add(item)
            items1.add(item1)
            items2.add(item2)
            items3.add(item3)
        }


        mList.add(TaskResponse(items, "Ijroda"))
        mList.add(TaskResponse(items1, "Muddati bugun"))
        mList.add(TaskResponse(items2, "Muddati ertaga"))
        mList.add(TaskResponse(items3, "Muddati indinga"))

        return mList
    }
}