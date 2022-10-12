package com.ilizma.schedule.view.adapter.factory

import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.view.adapter.DaysAdapter
import com.ilizma.schedule.view.bind.factory.DayBinderFactory
import com.ilizma.schedule.view.viewholder.factory.DayViewHolderFactory
import com.ilizma.view.adapter.factory.AdapterFactory
import com.ilizma.view.adapter.util.ItemDiffUtil

class DaysAdapterFactoryImp(
    private val binderFactory: DayBinderFactory,
    private val diffUtil: ItemDiffUtil<Day>,
    private val viewHolderFactory: DayViewHolderFactory,
) : AdapterFactory<Day> {

    override fun create(
    ): DaysAdapter = DaysAdapter(
        binderFactory = binderFactory,
        viewHolderFactory = viewHolderFactory,
        liveChannelItemDiffUtil = diffUtil,
    )

}