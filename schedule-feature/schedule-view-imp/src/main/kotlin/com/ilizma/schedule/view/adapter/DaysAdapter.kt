package com.ilizma.schedule.view.adapter

import android.view.ViewGroup
import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.view.bind.factory.DayBinderFactory
import com.ilizma.schedule.view.viewholder.factory.DayViewHolderFactory
import com.ilizma.view.adapter.Adapter
import com.ilizma.view.adapter.util.ItemDiffUtil
import com.ilizma.view.viewholder.ViewHolder

class DaysAdapter(
    private val binderFactory: DayBinderFactory,
    private val viewHolderFactory: DayViewHolderFactory,
    liveChannelItemDiffUtil: ItemDiffUtil<Day>,
) : Adapter<Day>(
    liveChannelItemDiffUtil
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder<Day> = viewHolderFactory.create(
        binderFactory = binderFactory,
        parent = parent,
    )

    override fun onBindViewHolder(
        holder: ViewHolder<Day>,
        position: Int,
    ) {
        getItem(position)
            .let { holder.bind(it) }
    }

    override fun onViewRecycled(
        holder: ViewHolder<Day>,
    ) {
        super.onViewRecycled(holder)
        holder.unBind()
    }

}