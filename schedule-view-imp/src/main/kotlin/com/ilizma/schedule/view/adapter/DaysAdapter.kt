package com.ilizma.schedule.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.view.adapter.util.DayDiffUtil
import com.ilizma.schedule.view.viewholder.DayViewHolder
import com.ilizma.schedule.view.viewholder.factory.DayViewHolderFactory

class DaysAdapter(
    private val viewHolderFactory: DayViewHolderFactory,
) : ListAdapter<Day, DayViewHolder>(
    DayDiffUtil()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DayViewHolder = viewHolderFactory.create(
        parent,
    )

    override fun onBindViewHolder(
        holder: DayViewHolder,
        position: Int,
    ) {
        getItem(position)
            .let { holder.bind(it) }
    }

    override fun onViewRecycled(
        holder: DayViewHolder,
    ) {
        super.onViewRecycled(holder)
        holder.unBind()
    }
}