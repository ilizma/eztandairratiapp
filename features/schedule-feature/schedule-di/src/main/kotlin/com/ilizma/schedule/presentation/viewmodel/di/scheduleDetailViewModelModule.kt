package com.ilizma.schedule.presentation.viewmodel.di

import android.content.res.Resources
import com.ilizma.resources.R
import com.ilizma.schedule.di.BuildConfig
import com.ilizma.schedule.presentation.mapper.ProgramTypeMapper
import com.ilizma.schedule.presentation.mapper.ScheduleStateMapper
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModelImp
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val scheduleDetailScreenViewModelModule: Module = module {

    viewModel<ScheduleDetailScreenViewModel> {
        ScheduleDetailScreenViewModelImp(
            saveCacheUseCase = get(),
            dayNameUseCase = get(),
            scheduleUseCase = get(),
            mapper = ProgramTypeMapper()
                .let { ScheduleStateMapper(it) },
            unknownErrorMessage = get<Resources>().getString(R.string.unknown_error),
            isDebug = BuildConfig.DEBUG,
            _scheduleState = MutableSharedFlow(),
            _navigationAction = MutableSharedFlow(),
        )
    }

}