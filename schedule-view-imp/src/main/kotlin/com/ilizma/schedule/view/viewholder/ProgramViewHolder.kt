package com.ilizma.schedule.view.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.ilizma.schedule.presentation.model.Program
import com.ilizma.schedule.view.databinding.ProgramItemBinding

class ProgramViewHolder(
    private val binding: ProgramItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        program: Program,
    ) {
        binding.programItemTvHour.text = program.hour
        binding.programItemTvName.text = program.name
        binding.programItemIvRepeated.isVisible = program.repeated
    }

    fun unBind() {
        binding.programItemIvRepeated.isVisible = false
    }

}