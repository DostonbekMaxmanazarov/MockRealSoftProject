package com.example.testrealsoftproject.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.databinding.FragmentTodayTasksBinding
import com.example.testrealsoftproject.datasource.network.response.TaskItemsResponse
import com.example.testrealsoftproject.datasource.network.response.TaskResponse
import com.example.testrealsoftproject.presentation.ui.adapter.TodayTasksAdapter
import com.example.testrealsoftproject.presentation.vm.TodayTaskViewModel
import com.example.testrealsoftproject.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class TodayTasksFragment : BaseFragmentImpl(R.layout.fragment_today_tasks) {

    private var _binding: FragmentTodayTasksBinding? = null
    private val binding get() = _binding!!

    private val vm by viewModels<TodayTaskViewModel>()

    private lateinit var adapter: TodayTasksAdapter

    private var taskResponse: TaskResponse? = null

    override fun search(key: String) {
        taskResponse?.data?.let { items ->
            vm.searchTasks(items, key)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTodayTasksBinding.bind(view)
        initArguments()
        initRv()
        loadData()
    }

    private fun initArguments() {
        taskResponse =
            requireArguments().getSerializable(Constants.Bundle.PUT_ARGUMENTS_TODAY_FRAGMENT) as TaskResponse?
    }

    private fun initRv() {
        adapter = TodayTasksAdapter()
        taskResponse?.data?.let {
            adapter.submitList(it as MutableList<TaskItemsResponse>)
        }
        binding.apply {
            rv.layoutManager = LinearLayoutManager(requireContext())
            rv.adapter = adapter
        }
    }

    private fun loadData() {
        vm.searchTaskStateFlow.onEach {
            adapter.submitList(it.toMutableList())
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}