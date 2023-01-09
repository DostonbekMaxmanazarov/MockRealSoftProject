package com.example.testrealsoftproject.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.databinding.FragmentLoginBinding
import com.example.testrealsoftproject.model.ResultData
import com.example.testrealsoftproject.model.UserAccount
import com.example.testrealsoftproject.presentation.vm.AuthViewModel
import com.example.testrealsoftproject.utils.extensions.changeStatusBar
import com.example.testrealsoftproject.utils.extensions.replaceFragment
import com.example.testrealsoftproject.utils.extensions.showOrHidePassword
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val vm by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
        changeStatusBar(R.color.color_gains_boro)
        viewsClicked()
        loadData()
    }

    private fun viewsClicked() {
        binding.apply {
            ivShowPassword.setOnClickListener {
                etPassword.showOrHidePassword(ivShowPassword)
            }

            btnNext.setOnClickListener {
                when {
                    binding.etLogin.text.isNullOrEmpty() -> {
                        binding.etLogin.error = "Qatorni to'ldiring!"
                        binding.etLogin.setBackgroundResource(R.drawable.bg_edit_text_error)
                    }
                    binding.etPassword.text.isNullOrEmpty() -> {
                        binding.etPassword.error = "Qatorni to'ldiring!"
                        binding.etPassword.setBackgroundResource(R.drawable.bg_edit_text_error)
                    }
                    else -> {
                        vm.check(
                            UserAccount(
                                binding.etLogin.text.toString(), binding.etPassword.text.toString()
                            )
                        )
                    }
                }
            }

            etLogin.addTextChangedListener {
                binding.etLogin.setBackgroundResource(R.drawable.bg_edit_text)
            }

            etPassword.addTextChangedListener {
                binding.etPassword.setBackgroundResource(R.drawable.bg_edit_text)
            }
        }
    }

    private fun loadData() {
        vm.successFlow.onEach {
            when (it) {
                is ResultData.Success -> {
                    if (it.value) replaceFragment(R.id.container, MainFragment())
                }
                is ResultData.Failure -> {
                    binding.tvErrorPasswordOrLogin.visibility = View.VISIBLE
                    binding.tvErrorPasswordOrLogin.text = it.strError
                    binding.etLogin.setBackgroundResource(R.drawable.bg_edit_text_error)
                    binding.etPassword.setBackgroundResource(R.drawable.bg_edit_text_error)
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}