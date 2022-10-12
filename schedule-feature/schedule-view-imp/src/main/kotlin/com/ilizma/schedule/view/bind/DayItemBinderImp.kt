package com.ilizma.schedule.view.bind

import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.databinding.DayItemBinding

class DayItemBinderImp(
    private val viewModel: ScheduleScreenViewModel,
) : DayItemBinder<Day> {

    private lateinit var binding: DayItemBinding

    override fun bind(binding: DayItemBinding, day: Day) {
        this.binding = binding
        binding.dayItemTv.text = day.name
        binding.dayItemTv.setOnClickListener {
            viewModel.onClick(day)
        }
    }

    override fun unBind() {
        binding.dayItemTv.setOnClickListener(null)
    }

}