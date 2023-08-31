package com.ilizma.schedule.presentation.viewmodel.factory

import com.ilizma.presentation.SingleLiveEvent
import com.ilizma.schedule.presentation.mapper.ScheduleStateMapper
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleState
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenDetailViewModelImp
import dagger.assisted.AssistedFactory
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableSharedFlow

@AssistedFactory
interface ScheduleDetailViewModelAssistedFactory {

    fun create(
        mapper: ScheduleStateMapper,
        backgroundScheduler: Scheduler,
        compositeDisposable: CompositeDisposable,
        unknownErrorMessage: String,
        isDebug: Boolean,
        _dayName: MutableSharedFlow<String>,
        _scheduleState: MutableSharedFlow<ScheduleState>,
        _navigationAction: SingleLiveEvent<ScheduleDetailNavigationAction>,
    ): ScheduleScreenDetailViewModelImp

}