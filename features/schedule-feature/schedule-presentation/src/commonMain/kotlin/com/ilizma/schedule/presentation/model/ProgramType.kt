package com.ilizma.schedule.presentation.model

sealed interface ProgramType {

    data class Item(
        val hour: String,
        val day: Int,
        val name: String,
        val repeated: Boolean,
    ) : ProgramType

    data object Loading : ProgramType
}