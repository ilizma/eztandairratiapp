package com.ilizma.schedule.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ilizma.presentation.SingleLiveEvent
import com.ilizma.schedule.presentation.mapper.ScheduleStateMapper
import com.ilizma.schedule.presentation.mapper.ProgramTypeMapper
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableSharedFlow

class ScheduleDetailViewModelFactory(
    private val scheduleDetailViewModelAssistedFactory: ScheduleDetailViewModelAssistedFactory,
    private val unknownErrorMessage: String,
    private val isDebug: Boolean,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = scheduleDetailViewModelAssistedFactory.create(
        mapper = ScheduleStateMapper(ProgramTypeMapper()),
        backgroundScheduler = Schedulers.io(),
        compositeDisposable = CompositeDisposable(),
        unknownErrorMessage = unknownErrorMessage,
        isDebug = isDebug,
        _dayName = MutableSharedFlow(),
        _scheduleState = MutableSharedFlow(),
        _navigationAction = SingleLiveEvent(),
    ) as T

}