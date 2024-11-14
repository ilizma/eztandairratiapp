package com.ilizma.schedule.domain.model

data class Program(
    val hour: String,
    val day: Int,
    val name: String,
    val repeated: Boolean,
)