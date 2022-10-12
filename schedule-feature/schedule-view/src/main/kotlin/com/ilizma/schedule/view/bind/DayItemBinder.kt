package com.ilizma.schedule.view.bind

import com.ilizma.schedule.view.databinding.DayItemBinding

interface DayItemBinder<T> {

    fun bind(binding: DayItemBinding, day: T)

    fun unBind()

}