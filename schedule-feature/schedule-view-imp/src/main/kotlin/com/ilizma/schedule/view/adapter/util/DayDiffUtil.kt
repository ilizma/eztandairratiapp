package com.ilizma.schedule.view.adapter.util

import androidx.recyclerview.widget.DiffUtil
import com.ilizma.schedule.presentation.model.Day

class DayDiffUtil : DiffUtil.ItemCallback<Day>() {

    override fun areItemsTheSame(
        oldItem: Day,
        newItem: Day,
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Day,
        newItem: Day,
    ): Boolean = oldItem == newItem
}