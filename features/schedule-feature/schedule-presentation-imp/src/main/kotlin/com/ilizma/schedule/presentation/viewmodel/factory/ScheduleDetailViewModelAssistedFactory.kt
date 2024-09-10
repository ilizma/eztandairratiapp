package com.ilizma.schedule.presentation.viewmodel.factory

import com.ilizma.schedule.presentation.mapper.ScheduleStateMapper
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleState
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenDetailViewModelImp
import dagger.assisted.AssistedFactory
import kotlinx.coroutines.flow.MutableSharedFlow

@AssistedFactory
interface ScheduleDetailViewModelAssistedFactory {

    fun create(
        mapper: ScheduleStateMapper,
        unknownErrorMessage: String,
        isDebug: Boolean,
        _scheduleState: MutableSharedFlow<ScheduleState>,
        _navigationAction: MutableSharedFlow<ScheduleDetailNavigationAction>,
    ): ScheduleScreenDetailViewModelImp

}