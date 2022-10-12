package com.ilizma.schedule.view.bind.factory

import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.bind.DayItemBinder
import com.ilizma.schedule.view.bind.DayItemBinderImp

class DayBinderFactory(
    private val viewModelLazy: Lazy<ScheduleScreenViewModel>,
) {

    fun create(
    ): DayItemBinder<Day> = DayItemBinderImp(
        viewModelLazy.value,
    )

}