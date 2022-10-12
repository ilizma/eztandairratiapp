package com.ilizma.schedule.presentation.viewmodel.factory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ilizma.presentation.SingleLiveEvent
import com.ilizma.schedule.presentation.mapper.ProgramListMapper
import com.ilizma.schedule.presentation.mapper.ProgramTypeMapper
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ScheduleDetailViewModelFactory(
    private val scheduleDetailViewModelAssistedFactory: ScheduleDetailViewModelAssistedFactory,
    private val unknownErrorMessage: String,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = scheduleDetailViewModelAssistedFactory.create(
        mapper = ProgramListMapper(ProgramTypeMapper()),
        backgroundScheduler = Schedulers.io(),
        compositeDisposable = CompositeDisposable(),
        unknownErrorMessage = unknownErrorMessage,
        _dayName = MutableLiveData(),
        _scheduleState = MutableLiveData(),
        _error = MutableLiveData(),
        _navigationAction = SingleLiveEvent(),
    ) as T

}