package com.ilizma.schedule.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ilizma.schedule.presentation.model.Program
import com.ilizma.schedule.view.adapter.util.ProgramDiffUtil
import com.ilizma.schedule.view.viewholder.ProgramViewHolder
import com.ilizma.schedule.view.viewholder.factory.ProgramViewHolderFactory

class ProgramsAdapter(
    private val viewHolderFactory: ProgramViewHolderFactory,
) : ListAdapter<Program, ProgramViewHolder>(
    ProgramDiffUtil()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ProgramViewHolder = viewHolderFactory.create(
        parent,
    )

    override fun onBindViewHolder(
        holder: ProgramViewHolder,
        position: Int,
    ) {
        getItem(position)
            .let { holder.bind(it) }
    }

    override fun onViewRecycled(
        holder: ProgramViewHolder,
    ) {
        super.onViewRecycled(holder)
        holder.unBind()
    }
}