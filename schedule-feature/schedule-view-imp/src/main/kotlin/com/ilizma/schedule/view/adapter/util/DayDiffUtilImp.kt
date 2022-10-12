package com.ilizma.schedule.view.adapter.util

import com.ilizma.schedule.presentation.model.Day
import com.ilizma.view.adapter.util.ItemDiffUtil

class DayDiffUtilImp : ItemDiffUtil<Day>() {

    override fun areItemsTheSame(
        oldItem: Day,
        newItem: Day,
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Day,
        newItem: Day,
    ): Boolean = oldItem == newItem
}