package com.ilizma.schedule.view.adapter.factory

import com.ilizma.schedule.view.adapter.ProgramsAdapter
import com.ilizma.schedule.view.viewholder.factory.ProgramViewHolderFactory

class ProgramsAdapterFactory(
    private val viewHolderFactory: ProgramViewHolderFactory,
) {

    fun create(
    ): ProgramsAdapter = ProgramsAdapter(
        viewHolderFactory,
    )

}