package com.ilizma.schedule.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.databinding.DayItemBinding

class DayViewHolder(
    private val binding: DayItemBinding,
    viewModelLazy: Lazy<ScheduleScreenViewModel>,
) : RecyclerView.ViewHolder(binding.root) {

    private val viewModel: ScheduleScreenViewModel by viewModelLazy

    fun bind(
        day: Day,
    ) {
        binding.dayItemTv.text = day.name
        binding.dayItemTv.setOnClickListener {
            viewModel.onClick(day)
        }
    }

    fun unBind() {
        binding.dayItemTv.setOnClickListener(null)
    }

}