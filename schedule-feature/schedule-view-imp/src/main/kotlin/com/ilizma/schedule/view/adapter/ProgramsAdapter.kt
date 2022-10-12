package com.ilizma.schedule.view.adapter

import android.view.ViewGroup
import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.view.bind.factory.ProgramBinderFactory
import com.ilizma.schedule.view.mapper.ProgramViewTypeMapper
import com.ilizma.schedule.view.model.ProgramViewType.TYPE_ITEM
import com.ilizma.schedule.view.model.ProgramViewType.TYPE_LOADING
import com.ilizma.schedule.view.viewholder.factory.ProgramViewHolderFactory
import com.ilizma.view.adapter.Adapter
import com.ilizma.view.adapter.util.ItemDiffUtil
import com.ilizma.view.viewholder.ViewHolder

class ProgramsAdapter(
    private val binderFactory: ProgramBinderFactory,
    liveChannelItemDiffUtil: ItemDiffUtil<ProgramType>,
    private val viewHolderFactory: ProgramViewHolderFactory,
    private val mapper: ProgramViewTypeMapper,
) : Adapter<ProgramType>(
    liveChannelItemDiffUtil,
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder<ProgramType> = viewHolderFactory.create(
        binderFactory = binderFactory,
        parent = parent,
        viewType = mapper.from(viewType),
    )

    override fun onBindViewHolder(
        holder: ViewHolder<ProgramType>,
        position: Int,
    ) {
        getItem(position)
            .let { holder.bind(it) }
    }

    override fun onViewRecycled(
        holder: ViewHolder<ProgramType>,
    ) {
        super.onViewRecycled(holder)
        holder.unBind()
    }

    override fun getItemViewType(
        position: Int,
    ): Int = when (getItem(position)) {
        is ProgramType.Loading -> TYPE_LOADING.ordinal
        else -> TYPE_ITEM.ordinal
    }

}