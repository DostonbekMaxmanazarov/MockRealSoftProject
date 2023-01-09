package com.example.testrealsoftproject.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.databinding.FragmentChatBinding
import com.example.testrealsoftproject.databinding.LayoutChatAppBarBinding
import com.example.testrealsoftproject.presentation.ui.adapter.ChatAdapter
import com.example.testrealsoftproject.presentation.vm.ChatViewModel
import com.example.testrealsoftproject.utils.extensions.changeStatusBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat) {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var appBarBinding: LayoutChatAppBarBinding

    private lateinit var adapter: ChatAdapter

    private val vm by viewModels<ChatViewModel>()

    private var strName: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChatBinding.bind(view)
        appBarBinding = binding.appBar
        changeStatusBar(R.color.white)
        strName = requireArguments().getString("NAME")
        initView()
        viewClicked()
        loadData()
    }

    private fun initView() {
        adapter = ChatAdapter()
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter
        appBarBinding.tvTitle.text = strName
    }

    private fun viewClicked() {
        appBarBinding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun loadData() {
        vm.chatUsers.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}