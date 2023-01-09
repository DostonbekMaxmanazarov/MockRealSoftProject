package com.example.testrealsoftproject.presentation.ui.fragment

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragmentImpl(@LayoutRes layoutId: Int) : Fragment(layoutId) {
    abstract fun search(key: String)
}