package com.example.testrealsoftproject.presentation.ui.fragment

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.databinding.FragmentMainBinding
import com.example.testrealsoftproject.enum.FragmentTypes
import com.example.testrealsoftproject.utils.extensions.changeStatusBar
import com.example.testrealsoftproject.utils.extensions.replaceFragment
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), NavigationBarView.OnItemSelectedListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var mCurrentFragment = FragmentTypes.TODAY_FRAGMENT


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        changeStatusBar(R.color.white)
        initView()
    }

    private fun initView() {
        binding.bottomNavigationView.setOnItemSelectedListener(this)

        replaceFragment(container = R.id.frameContainer, fragment = TodayFragment())
        mCurrentFragment = FragmentTypes.TODAY_FRAGMENT
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionToday -> {
                if (mCurrentFragment != FragmentTypes.TODAY_FRAGMENT) {
                    replaceFragment(container = R.id.frameContainer, fragment = TodayFragment())
                    mCurrentFragment = FragmentTypes.TODAY_FRAGMENT
                }
            }

            R.id.actionDocuments -> {
                if (mCurrentFragment != FragmentTypes.DOCUMENTS_FRAGMENT) {
                    replaceFragment(container = R.id.frameContainer, fragment = DocumentsFragment())
                    mCurrentFragment = FragmentTypes.DOCUMENTS_FRAGMENT
                }
            }

            R.id.actionChat -> {
                if (mCurrentFragment != FragmentTypes.CHAT_FRAGMENT) {
                    replaceFragment(container = R.id.frameContainer, fragment = ChatMainFragment())
                    mCurrentFragment = FragmentTypes.CHAT_FRAGMENT
                }
            }

            R.id.actionWorkPlan -> {
                if (mCurrentFragment != FragmentTypes.WORK_PLAN_FRAGMENT) {
                    replaceFragment(container = R.id.frameContainer, fragment = WorkPlanFragment())
                    mCurrentFragment = FragmentTypes.WORK_PLAN_FRAGMENT
                }
            }

            R.id.actionTask -> {
                if (mCurrentFragment != FragmentTypes.TASK_FRAGMENT) {
                    replaceFragment(container = R.id.frameContainer, fragment = TasksFragment())
                    mCurrentFragment = FragmentTypes.TASK_FRAGMENT
                }
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}