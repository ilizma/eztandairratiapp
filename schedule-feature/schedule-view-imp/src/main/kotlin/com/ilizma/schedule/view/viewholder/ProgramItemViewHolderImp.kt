package com.ilizma.schedule.view.viewholder

import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.view.bind.ProgramItemBinder
import com.ilizma.schedule.view.databinding.ProgramItemBinding
import com.ilizma.view.viewholder.ViewHolder

class ProgramItemViewHolderImp(
    private val binder: ProgramItemBinder<ProgramType.Item>,
    private val binding: ProgramItemBinding,
) : ViewHolder<ProgramType>(binding.root) {

    override fun bind(
        data: ProgramType,
    ) {
        binder.bind(binding, data as ProgramType.Item)
    }

    override fun unBind() {
        binder.unBind()
    }

}