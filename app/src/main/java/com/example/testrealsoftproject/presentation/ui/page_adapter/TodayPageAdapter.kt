package com.example.testrealsoftproject.presentation.ui.page_adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testrealsoftproject.datasource.network.response.TaskResponse
import com.example.testrealsoftproject.presentation.ui.fragment.BaseFragmentImpl
import com.example.testrealsoftproject.presentation.ui.fragment.TodayTasksFragment
import com.example.testrealsoftproject.utils.Constants
import com.example.testrealsoftproject.utils.extensions.putArguments

class TodayPageAdapter(
    private var data: List<TaskResponse>, activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    private var fragments: MutableList<BaseFragmentImpl> = mutableListOf()

    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): BaseFragmentImpl {
        val fm = TodayTasksFragment().putArguments {
            putSerializable(Constants.Bundle.PUT_ARGUMENTS_TODAY_FRAGMENT, data[position])
        }

        fragments.add(fm)
        return fm
    }

    fun getFragments() = fragments
}