package com.ilizma.schedule.presentation.viewmodel.factory

import androidx.lifecycle.MutableLiveData
import com.ilizma.presentation.SingleLiveEvent
import com.ilizma.schedule.presentation.mapper.ProgramListMapper
import com.ilizma.schedule.presentation.model.ScheduleState
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailViewModelImp
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable

const val ERROR_ASSISTED = "ERROR_ASSISTED"

@AssistedFactory
interface ScheduleDetailViewModelAssistedFactory {

    fun create(
        mapper: ProgramListMapper,
        backgroundScheduler: Scheduler,
        compositeDisposable: CompositeDisposable,
        unknownErrorMessage: String,
        _dayName: MutableLiveData<String>,
        _scheduleState: MutableLiveData<ScheduleState>,
        @Assisted(ERROR_ASSISTED) _error: MutableLiveData<String>,
        _navigationAction: SingleLiveEvent<ScheduleDetailNavigationAction>,
    ): ScheduleDetailViewModelImp

}