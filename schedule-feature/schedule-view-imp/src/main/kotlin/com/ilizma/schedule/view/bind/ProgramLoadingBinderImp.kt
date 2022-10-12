package com.ilizma.schedule.view.bind

import com.ilizma.schedule.view.databinding.ProgramLoadingBinding

class ProgramLoadingBinderImp : ProgramLoadingBinder {

    private lateinit var binding: ProgramLoadingBinding

    override fun bind(
        binding: ProgramLoadingBinding,
    ) {
        this.binding = binding
        binding.programLoadingSfl.startShimmer()
    }

    override fun unBind() {
        binding.programLoadingSfl.stopShimmer()
    }

}