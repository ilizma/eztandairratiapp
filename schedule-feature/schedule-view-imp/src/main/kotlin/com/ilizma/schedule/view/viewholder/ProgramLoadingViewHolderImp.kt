package com.ilizma.schedule.view.viewholder

import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.view.bind.ProgramLoadingBinder
import com.ilizma.schedule.view.databinding.ProgramLoadingBinding
import com.ilizma.view.viewholder.ViewHolder

class ProgramLoadingViewHolderImp(
    private val binder: ProgramLoadingBinder,
    private val binding: ProgramLoadingBinding,
) : ViewHolder<ProgramType>(binding.root) {

    override fun bind(
        data: ProgramType,
    ) {
        binder.bind(binding)
    }

    override fun unBind() {
        binder.unBind()
    }

}