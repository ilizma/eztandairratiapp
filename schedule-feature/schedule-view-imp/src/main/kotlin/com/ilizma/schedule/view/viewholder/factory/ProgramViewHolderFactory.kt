package com.ilizma.schedule.view.viewholder.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.view.bind.factory.ProgramBinderFactory
import com.ilizma.schedule.view.databinding.ProgramItemBinding
import com.ilizma.schedule.view.databinding.ProgramLoadingBinding
import com.ilizma.schedule.view.model.ProgramViewType
import com.ilizma.schedule.view.viewholder.ProgramItemViewHolderImp
import com.ilizma.schedule.view.viewholder.ProgramLoadingViewHolderImp
import com.ilizma.view.viewholder.ViewHolder

class ProgramViewHolderFactory {

    fun create(
        binderFactory: ProgramBinderFactory,
        parent: ViewGroup,
        viewType: ProgramViewType,
    ): ViewHolder<ProgramType> = LayoutInflater.from(parent.context)
        .let { viewHolder(binderFactory, viewType, it, parent) }

    private fun viewHolder(
        binderFactory: ProgramBinderFactory,
        viewType: ProgramViewType,
        inflater: LayoutInflater,
        parent: ViewGroup,
    ): ViewHolder<ProgramType> = when (viewType) {
        ProgramViewType.TYPE_LOADING -> ProgramLoadingBinding.inflate(inflater, parent, false)
            .let { ProgramLoadingViewHolderImp(binder = binderFactory.createLoading(), binding = it) }
        ProgramViewType.TYPE_ITEM -> ProgramItemBinding.inflate(inflater, parent, false)
            .let { ProgramItemViewHolderImp(binder = binderFactory.createItem(), binding = it) }
        else -> throw IllegalArgumentException("Invalid ProgramViewType: $viewType")
    }

}