package com.ilizma.schedule.presentation.model

data class Program(
    val hour: String,
    val day: Int,
    val name: String,
    val repeated: Boolean,
)