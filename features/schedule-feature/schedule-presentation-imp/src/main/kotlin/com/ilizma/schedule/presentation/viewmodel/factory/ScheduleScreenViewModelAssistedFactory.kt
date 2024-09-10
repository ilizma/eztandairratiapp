package com.ilizma.schedule.presentation.viewmodel.factory

import com.ilizma.schedule.presentation.mapper.DaysMapper
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModelImp
import dagger.assisted.AssistedFactory
import kotlinx.coroutines.flow.MutableSharedFlow

@AssistedFactory
interface ScheduleScreenViewModelAssistedFactory {

    fun create(
        mapper: DaysMapper,
        _navigationAction: MutableSharedFlow<ScheduleScreenNavigationAction>
    ): ScheduleScreenViewModelImp

}