package com.ilizma.schedule.view.adapter.factory

import com.ilizma.schedule.view.adapter.DaysAdapter
import com.ilizma.schedule.view.viewholder.factory.DayViewHolderFactory

class DaysAdapterFactory(
    private val viewHolderFactory: DayViewHolderFactory,
) {

    fun create(
    ): DaysAdapter = DaysAdapter(
        viewHolderFactory,
    )

}