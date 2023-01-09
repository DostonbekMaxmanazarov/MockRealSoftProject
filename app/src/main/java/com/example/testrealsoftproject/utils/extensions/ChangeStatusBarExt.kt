package com.example.testrealsoftproject.utils.extensions

import android.os.Build
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment

fun Fragment.changeStatusBar(color: Int, isIconLight: Boolean = false) {
    val window: Window = requireActivity().window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    if (isIconLight)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
        }
     else
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), color)
    }
}