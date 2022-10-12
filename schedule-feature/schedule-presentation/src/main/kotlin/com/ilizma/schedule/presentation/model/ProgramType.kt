package com.ilizma.schedule.presentation.model

sealed class ProgramType {

    data class Item(
        val hour: String,
        val day: Int,
        val name: String,
        val repeated: Boolean,
    ) : ProgramType()

    object Loading : ProgramType()
}