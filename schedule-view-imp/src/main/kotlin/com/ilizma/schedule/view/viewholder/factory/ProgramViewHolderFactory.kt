package com.ilizma.schedule.view.viewholder.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.databinding.ProgramItemBinding
import com.ilizma.schedule.view.viewholder.ProgramViewHolder

class ProgramViewHolderFactory(
    private val viewModelLazy: Lazy<ScheduleScreenViewModel>,
) {

    fun create(
        parent: ViewGroup,
    ): ProgramViewHolder = LayoutInflater.from(parent.context)
        .let { ProgramItemBinding.inflate(it, parent, false) }
        .let { ProgramViewHolder(it, viewModelLazy) }

}