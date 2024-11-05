package com.ilizma.schedule.presentation.viewmodel.di

import com.ilizma.schedule.presentation.mapper.DayListMapper
import com.ilizma.schedule.presentation.mapper.DayMapper
import com.ilizma.schedule.presentation.mapper.DaysMapper
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModelImp
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val scheduleScreenViewModelModule: Module = module {

    viewModel<ScheduleScreenViewModel> {
        ScheduleScreenViewModelImp(
            useCase = get(),
            mapper = DayMapper()
                .let { DayListMapper(it) }
                .let { DaysMapper(it) },
            _navigationAction = MutableSharedFlow(),
        )
    }

}