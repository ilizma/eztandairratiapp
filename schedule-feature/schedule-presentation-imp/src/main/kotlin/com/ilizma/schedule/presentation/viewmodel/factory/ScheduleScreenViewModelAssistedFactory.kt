package com.ilizma.schedule.presentation.viewmodel.factory

import com.ilizma.presentation.SingleLiveEvent
import com.ilizma.schedule.presentation.mapper.DaysMapper
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModelImp
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ScheduleScreenViewModelAssistedFactory {

    fun create(
        mapper: DaysMapper,
        _navigationAction: SingleLiveEvent<ScheduleScreenNavigationAction>
    ): ScheduleScreenViewModelImp

}