package com.ilizma.schedule.presentation.viewmodel.di

import com.ilizma.schedule.di.BuildConfig
import com.ilizma.schedule.presentation.mapper.ProgramTypeMapper
import com.ilizma.schedule.presentation.mapper.ScheduleStateMapper
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModelImp
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import kotlin.let

actual val scheduleDetailScreenViewModelModule: Module = module {

    viewModel<ScheduleDetailScreenViewModel> {
        ScheduleDetailScreenViewModelImp(
            saveCacheUseCase = get(),
            dayNameUseCase = get(),
            scheduleUseCase = get(),
            mapper = ProgramTypeMapper()
                .let { ScheduleStateMapper(it) },
            isDebug = BuildConfig.DEBUG,
            _scheduleState = MutableSharedFlow(),
            _navigationAction = MutableSharedFlow(),
        )
    }

}