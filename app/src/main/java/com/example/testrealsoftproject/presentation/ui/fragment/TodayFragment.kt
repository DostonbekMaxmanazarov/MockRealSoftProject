package com.example.testrealsoftproject.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.databinding.FragmentTodayBinding
import com.example.testrealsoftproject.databinding.LayoutAppBarBinding
import com.example.testrealsoftproject.datasource.network.response.TaskResponse
import com.example.testrealsoftproject.model.TabData
import com.example.testrealsoftproject.presentation.ui.adapter.TodayTabAdapter
import com.example.testrealsoftproject.presentation.ui.page_adapter.TodayPageAdapter
import com.example.testrealsoftproject.utils.extensions.changeStatusBar
import com.example.testrealsoftproject.utils.extensions.onPageChangeCallback
import com.example.testrealsoftproject.presentation.vm.TodayViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class TodayFragment : Fragment(R.layout.fragment_today) {

    private lateinit var appBarBinding: LayoutAppBarBinding
    private var _binding: FragmentTodayBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabAdapter: TodayTabAdapter
    private lateinit var pageAdapter: TodayPageAdapter

    private val vm by viewModels<TodayViewModel>()

    private var data = mutableListOf<TaskResponse>()
    private var tabData = mutableListOf<TabData>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTodayBinding.bind(view)
        appBarBinding = binding.appBar
        changeStatusBar(R.color.white)
        loadData()
        initView()
        viewClicked()
    }

    private fun viewClicked() {
        binding.apply {
            tabAdapter.setOnClickListener { position ->
                rv.smoothScrollToPosition(position)
                viewPager.setCurrentItem(position, true)
                appBarBinding.etSearch.setText("")
                visibleAppBar()
            }
            viewPager.onPageChangeCallback {
                rv.smoothScrollToPosition(it)
                tabAdapter.updateData(it)
                appBarBinding.etSearch.setText("")
                visibleAppBar()
            }
        }

        appBarBinding.ivSearch.setOnClickListener {
            inVisibleAppBar()
        }

        appBarBinding.tvCancel.setOnClickListener {
            visibleAppBar()
        }

        appBarBinding.ivCancel.setOnClickListener {
            appBarBinding.etSearch.setText("")
        }

        appBarBinding.etSearch.addTextChangedListener {
            pageAdapter.getFragments().getOrNull(binding.viewPager.currentItem)
                ?.search(it.toString())
        }
    }

    private fun initView() {
        visibleAppBar()
        pageAdapter = TodayPageAdapter(data, requireActivity())
        tabAdapter = TodayTabAdapter(requireContext())
        tabAdapter.submitList(tabData)

        binding.apply {
            rv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rv.adapter = tabAdapter

            viewPager.offscreenPageLimit = 4
            viewPager.adapter = pageAdapter
            //viewPager.setCurrentItem(1, false)
        }

        //tabAdapter.updateData(1)
    }

    private fun loadData() {
        var index = 0
        var msgCount: Int?

        vm.taskStateFlow.onEach {
            data = it.toMutableList()
            it.forEach { task ->
                msgCount = if (index == 0) 18 else if (index == 2) 6 else null
                tabData.add(TabData(task.title, msgCount))
                index++
            }
            binding.ivNext.visibility = View.VISIBLE
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun visibleAppBar() {
        appBarBinding.apply {
            ivCancel.visibility = View.GONE
            etSearch.visibility = View.GONE
            tvCancel.visibility = View.GONE
            tvTitle.visibility = View.VISIBLE
            ivSearch.visibility = View.VISIBLE
            ivCalendar.visibility = View.VISIBLE
            ivPersonPhoto.visibility = View.VISIBLE
            ivNotification.visibility = View.VISIBLE
        }
    }

    private fun inVisibleAppBar() {
        appBarBinding.apply {
            tvTitle.visibility = View.GONE
            ivCalendar.visibility = View.GONE
            ivPersonPhoto.visibility = View.GONE
            ivNotification.visibility = View.GONE
            ivCancel.visibility = View.VISIBLE
            etSearch.visibility = View.VISIBLE
            tvCancel.visibility = View.VISIBLE
            ivSearch.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}