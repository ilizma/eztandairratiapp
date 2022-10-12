package com.ilizma.schedule.view.bind

import androidx.core.view.isVisible
import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.view.databinding.ProgramItemBinding

class ProgramItemBinderImp : ProgramItemBinder<ProgramType.Item> {

    private lateinit var binding: ProgramItemBinding

    override fun bind(binding: ProgramItemBinding, program: ProgramType.Item) {
        this.binding = binding
        setupView(binding, program)
    }

    override fun unBind() {
        binding.programItemIvRepeated.isVisible = false
    }

    private fun setupView(
        binding: ProgramItemBinding,
        program: ProgramType.Item,
    ) {
        binding.programItemTvHour.text = program.hour
        binding.programItemTvName.text = program.name
        binding.programItemIvRepeated.isVisible = program.repeated
    }

}