package com.ilizma.schedule.view.viewholder.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.view.bind.factory.DayBinderFactory
import com.ilizma.schedule.view.databinding.DayItemBinding
import com.ilizma.schedule.view.viewholder.DayViewHolderImp
import com.ilizma.view.viewholder.ViewHolder

class DayViewHolderFactory {

    fun create(
        binderFactory: DayBinderFactory,
        parent: ViewGroup,
    ): ViewHolder<Day> = LayoutInflater.from(parent.context)
        .let { DayItemBinding.inflate(it, parent, false) }
        .let { DayViewHolderImp(binderFactory.create(), it) }

}