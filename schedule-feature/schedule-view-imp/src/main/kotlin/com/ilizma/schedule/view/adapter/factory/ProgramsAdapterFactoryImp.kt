package com.ilizma.schedule.view.adapter.factory

import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.view.adapter.ProgramsAdapter
import com.ilizma.schedule.view.bind.factory.ProgramBinderFactory
import com.ilizma.schedule.view.mapper.ProgramViewTypeMapper
import com.ilizma.schedule.view.viewholder.factory.ProgramViewHolderFactory
import com.ilizma.view.adapter.factory.AdapterFactory
import com.ilizma.view.adapter.util.ItemDiffUtil

class ProgramsAdapterFactoryImp(
    private val binderFactory: ProgramBinderFactory,
    private val diffUtil: ItemDiffUtil<ProgramType>,
    private val viewHolderFactory: ProgramViewHolderFactory,
) : AdapterFactory<ProgramType> {

    override fun create(
    ): ProgramsAdapter = ProgramsAdapter(
        binderFactory = binderFactory,
        liveChannelItemDiffUtil = diffUtil,
        viewHolderFactory = viewHolderFactory,
        mapper = ProgramViewTypeMapper(),
    )

}