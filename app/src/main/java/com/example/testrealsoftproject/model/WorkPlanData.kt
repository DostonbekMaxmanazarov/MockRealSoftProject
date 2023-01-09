package com.example.testrealsoftproject.model

import java.io.Serializable

data class WorkPlanData(
    val message: String? = null, val voice: Boolean = false, var isSelected:Boolean = false
) : Serializable
