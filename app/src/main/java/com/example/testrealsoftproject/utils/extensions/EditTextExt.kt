package com.example.testrealsoftproject.utils.extensions

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.example.testrealsoftproject.R

fun AppCompatEditText.showOrHidePassword(imageView: AppCompatImageView) {
    if (this.transformationMethod.equals(PasswordTransformationMethod.getInstance())) {
        imageView.setImageResource(R.drawable.ic_password_visibility)
        this.transformationMethod = HideReturnsTransformationMethod.getInstance()
        this.text?.length?.let { this.setSelection(it) }
    } else {
        imageView.setImageResource(R.drawable.ic_password_visibility_off)
        this.transformationMethod = PasswordTransformationMethod.getInstance()
        this.text?.length?.let { this.setSelection(it) }
    }
}