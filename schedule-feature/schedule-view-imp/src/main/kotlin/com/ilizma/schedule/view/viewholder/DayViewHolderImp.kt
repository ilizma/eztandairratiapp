package com.ilizma.schedule.view.viewholder

import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.view.bind.DayItemBinder
import com.ilizma.schedule.view.databinding.DayItemBinding
import com.ilizma.view.viewholder.ViewHolder

class DayViewHolderImp(
    private val binder: DayItemBinder<Day>,
    private val binding: DayItemBinding,
) : ViewHolder<Day>(binding.root) {

    override fun bind(
        data: Day,
    ) {
        binder.bind(binding, data)
    }

    override fun unBind() {
        binder.unBind()
    }

}