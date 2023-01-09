package com.example.testrealsoftproject.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.databinding.FragmentChatMainBinding
import com.example.testrealsoftproject.databinding.LayoutAppBarBinding
import com.example.testrealsoftproject.presentation.ui.adapter.ChatMainAdapter
import com.example.testrealsoftproject.presentation.vm.ChatMainViewModel
import com.example.testrealsoftproject.utils.extensions.changeStatusBar
import com.example.testrealsoftproject.utils.extensions.replaceFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ChatMainFragment : Fragment(R.layout.fragment_chat_main) {

    private var _binding: FragmentChatMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var appBarBinding: LayoutAppBarBinding

    private lateinit var adapter: ChatMainAdapter

    private val vm by viewModels<ChatMainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChatMainBinding.bind(view)
        appBarBinding = binding.appBar
        changeStatusBar(R.color.white)
        visibleAppBar()
        initView()
        viewClicked()
        loadData()
    }

    private fun initView() {
        adapter = ChatMainAdapter()
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter
    }

    private fun viewClicked() {
        adapter.setOnClickListener {
            val bundle = bundleOf("NAME" to it.name)
            replaceFragment(
                container = R.id.mainContainer,
                fragment = ChatFragment(),
                addToBackStack = true,
                args = bundle
            )
        }
    }

    private fun loadData() {
        vm.chatUsers.onEach {
            adapter.submitList(it.toMutableList())
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun visibleAppBar() {
        appBarBinding.apply {
            ivEdit.visibility = View.VISIBLE
            ivSearch.visibility = View.VISIBLE
            tvTitle.visibility = View.VISIBLE
            ivNotification.visibility = View.VISIBLE
            ivPersonPhoto.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}