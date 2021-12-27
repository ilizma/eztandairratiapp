package com.ilizma.schedule.view.adapter.util

import androidx.recyclerview.widget.DiffUtil
import com.ilizma.schedule.presentation.model.Program

class ProgramDiffUtil : DiffUtil.ItemCallback<Program>() {

    override fun areItemsTheSame(
        oldItem: Program,
        newItem: Program,
    ): Boolean =
        "${oldItem.day}${oldItem.hour}${oldItem.name}${oldItem.repeated}" == "${newItem.day}${newItem.hour}${newItem.name}${newItem.repeated}"

    override fun areContentsTheSame(
        oldItem: Program,
        newItem: Program,
    ): Boolean = oldItem == newItem
}