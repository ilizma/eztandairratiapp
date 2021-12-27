package com.ilizma.schedule.view.viewholder.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.databinding.DayItemBinding
import com.ilizma.schedule.view.viewholder.DayViewHolder

class DayViewHolderFactory(
    private val viewModelLazy: Lazy<ScheduleScreenViewModel>,
) {

    fun create(
        parent: ViewGroup,
    ): DayViewHolder = LayoutInflater.from(parent.context)
        .let { DayItemBinding.inflate(it, parent, false) }
        .let { DayViewHolder(it, viewModelLazy) }

}