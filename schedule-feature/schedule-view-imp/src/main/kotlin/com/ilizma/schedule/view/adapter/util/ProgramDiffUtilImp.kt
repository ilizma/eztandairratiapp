package com.ilizma.schedule.view.adapter.util

import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.view.adapter.util.ItemDiffUtil

class ProgramDiffUtilImp : ItemDiffUtil<ProgramType>() {

    override fun areItemsTheSame(
        oldItem: ProgramType,
        newItem: ProgramType,
    ): Boolean = when (oldItem) {
        ProgramType.Loading -> newItem is ProgramType.Loading
        is ProgramType.Item -> if (newItem is ProgramType.Item) {
            "${oldItem.day}${oldItem.hour}${oldItem.name}${oldItem.repeated}" == "${newItem.day}${newItem.hour}${newItem.name}${newItem.repeated}"
        } else {
            false
        }
    }

    override fun areContentsTheSame(
        oldItem: ProgramType,
        newItem: ProgramType,
    ): Boolean = oldItem == newItem

}