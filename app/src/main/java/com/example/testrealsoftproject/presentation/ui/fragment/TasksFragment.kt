package com.example.testrealsoftproject.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.databinding.FragmentChatBinding
import com.example.testrealsoftproject.databinding.FragmentDocumentsBinding
import com.example.testrealsoftproject.databinding.FragmentTasksBinding
import com.example.testrealsoftproject.databinding.LayoutChatAppBarBinding
import com.example.testrealsoftproject.presentation.ui.adapter.ChatAdapter
import com.example.testrealsoftproject.presentation.vm.ChatViewModel
import com.example.testrealsoftproject.utils.extensions.changeStatusBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks) {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTasksBinding.bind(view)
        changeStatusBar(R.color.white)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}