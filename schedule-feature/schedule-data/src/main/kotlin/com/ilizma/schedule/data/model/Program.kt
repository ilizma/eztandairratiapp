package com.ilizma.schedule.data.model

import androidx.annotation.Keep

@Keep
data class Program(
    val hour: String,
    val day: Int,
    val name: String,
    val repeated: Boolean,
)