package com.example.testrealsoftproject.utils.extensions

import android.app.Activity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.example.testrealsoftproject.presentation.ui.fragment.BaseFragmentImpl

fun BaseFragmentImpl.putArguments(
    block: Bundle.() -> Unit
): BaseFragmentImpl {
    val bundle = arguments ?: Bundle()
    block(bundle)
    arguments = bundle
    return this
}

fun Fragment.addFragment(
    @IdRes container: Int, fragment: Fragment, addToBackStack: Boolean = false
) {
    requireActivity().supportFragmentManager.commit {
        setReorderingAllowed(true)
        add(container, fragment)

        if (addToBackStack) addToBackStack(fragment.javaClass.simpleName)
    }
}

fun Fragment.replaceFragment(
    @IdRes container: Int, fragment: Fragment, addToBackStack: Boolean = false, args: Bundle? = null
) {
    requireActivity().supportFragmentManager.commit {
        setReorderingAllowed(true)
        fragment.arguments = args
        replace(container, fragment)

        if (addToBackStack) addToBackStack(fragment.javaClass.simpleName)
    }
}

fun FragmentActivity.replaceFragment(
    @IdRes container: Int, fragment: Fragment, addToBackStack: Boolean = false
) {
    this.supportFragmentManager.commit {
        setReorderingAllowed(true)
        replace(container, fragment)

        if (addToBackStack) addToBackStack(fragment.javaClass.simpleName)
    }
}