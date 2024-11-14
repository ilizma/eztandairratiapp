package com.ilizma.schedule.presentation.viewmodel.di

import com.ilizma.schedule.presentation.mapper.DayMapper
import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.presentation.model.Days
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModelImp
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val scheduleScreenViewModelModule: Module = module {

    viewModel<ScheduleScreenViewModel> {
        ScheduleScreenViewModelImp(
            mapper = DayMapper(),
            _days = listOf<Day>()
                .let { Days(it) }
                .let { MutableStateFlow(it) },
            _navigationAction = MutableSharedFlow(),
        )
    }

}