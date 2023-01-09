package com.example.testrealsoftproject.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.databinding.FragmentWorkPlanBinding
import com.example.testrealsoftproject.databinding.LayoutAppBarBinding
import com.example.testrealsoftproject.model.WeekData
import com.example.testrealsoftproject.presentation.ui.adapter.WeekDayAdapter
import com.example.testrealsoftproject.presentation.ui.adapter.WorkPlanAdapter
import com.example.testrealsoftproject.presentation.vm.WorkPlanViewModel
import com.example.testrealsoftproject.utils.extensions.changeStatusBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*

@AndroidEntryPoint
class WorkPlanFragment : Fragment(R.layout.fragment_work_plan) {

    private var _binding: FragmentWorkPlanBinding? = null
    private val binding get() = _binding!!
    private lateinit var appBarBinding: LayoutAppBarBinding

    private lateinit var adapter: WorkPlanAdapter
    private lateinit var weekAdapter: WeekDayAdapter

    private val vm by viewModels<WorkPlanViewModel>()

    private var isExpandable = false

    private var animLeft: Animation? = null
    private var animRight: Animation? = null

    private var mCalendar = Calendar.getInstance()


    private val mDateListener =
        CalendarView.OnDateChangeListener { _, year, montOfYear, dayOfMonth ->
            mCalendar.set(Calendar.YEAR, year)
            mCalendar.set(Calendar.MONTH, montOfYear)
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWorkPlanBinding.bind(view)
        appBarBinding = binding.appBar
        changeStatusBar(R.color.white)
        visibleAppBar()
        initView()
        viewClicked()
        loadData()
    }

    private fun initView() {
        animLeft = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_left)
        animRight = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_right)
        adapter = WorkPlanAdapter()
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter

        weekAdapter = WeekDayAdapter()
        binding.rvWeekData.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvWeekData.adapter = weekAdapter
        weekAdapter.submitList(loadWeekDay().toMutableList())
    }

    private fun viewClicked() {
        appBarBinding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.calendarView.setOnDateChangeListener(mDateListener)

        binding.expandableLayout.setOnClickListener {
            binding.expandable.isExpanded = !isExpandable
            isExpandable = !isExpandable
            animateImageDropDown(isExpandable)

            if (isExpandable) {
                binding.rvWeekData.visibility = View.GONE
            } else {
                binding.rvWeekData.visibility = View.VISIBLE
            }
        }
    }

    private fun animateImageDropDown(status: Boolean) {
        if (status) binding.ivArrowBottom.animate().rotation(0f).setDuration(500).start()
        else binding.ivArrowBottom.animate().rotation(180f).setDuration(500).start()
    }

    private fun loadData() {
        vm.taskStateFlow.onEach {
            adapter.submitList(it.toMutableList())
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun visibleAppBar() {
        appBarBinding.apply {
            ivSearch.visibility = View.VISIBLE
            tvTitle.visibility = View.VISIBLE
            tvTitle.text = "Yanvar"
            ivNotification.visibility = View.VISIBLE
            ivPersonPhoto.visibility = View.VISIBLE

        }
    }

    private fun loadWeekDay(): List<WeekData> {
        val weekDays = mutableMapOf<String, Int>()
        val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        var dayMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        for (i in day downTo 1) {
            weekDays[getDay(i)] = dayMonth
            dayMonth -= 1

        }

        dayMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        for (i in day + 1..7) {
            dayMonth += 1
            weekDays[getDay(i)] = dayMonth
        }

        val list = mutableListOf<WeekData>()

        list.add(0, WeekData("Ya", weekDays["Ya"]))
        list.add(1, WeekData("Du", weekDays["Du"]))
        list.add(2, WeekData("Se", weekDays["Se"]))
        list.add(3, WeekData("Cho", weekDays["Cho"]))
        list.add(4, WeekData("Pa", weekDays["Pa"]))
        list.add(5, WeekData("Ju", weekDays["Ju"]))
        list.add(6, WeekData("Sha", weekDays["Sha"]))

        return list
    }

    private fun getDay(day: Int): String = when (day) {
        1 -> "Ya"
        2 -> "Du"
        3 -> "Se"
        4 -> "Cho"
        5 -> "Pa"
        6 -> "Ju"
        7 -> "Sha"
        else -> "Time has stopped"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}