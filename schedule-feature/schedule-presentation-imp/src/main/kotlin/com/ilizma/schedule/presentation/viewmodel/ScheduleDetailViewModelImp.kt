package com.ilizma.schedule.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilizma.presentation.SingleLiveEvent
import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.domain.usecase.DayNameUseCase
import com.ilizma.schedule.domain.usecase.ScheduleUseCase
import com.ilizma.schedule.presentation.mapper.ProgramListMapper
import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.presentation.model.ScheduleState as PresentationScheduleState
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction.Back
import com.ilizma.schedule.presentation.viewmodel.factory.ERROR_ASSISTED
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo

class ScheduleDetailViewModelImp @AssistedInject constructor(
    dayNameUseCase: DayNameUseCase,
    private val scheduleUseCase: ScheduleUseCase,
    @Assisted private val mapper: ProgramListMapper,
    @Assisted private val backgroundScheduler: Scheduler,
    @Assisted private val compositeDisposable: CompositeDisposable,
    @Assisted private val unknownErrorMessage: String,
    @Assisted private val _dayName: MutableLiveData<String>,
    @Assisted private val _scheduleState: MutableLiveData<PresentationScheduleState>,
    @Assisted(ERROR_ASSISTED) private val _error: MutableLiveData<String>,
    @Assisted private val _navigationAction: SingleLiveEvent<ScheduleDetailNavigationAction>,
) : ScheduleDetailViewModel() {

    override val dayName: LiveData<String> = _dayName
    override val scheduleState: LiveData<PresentationScheduleState> = _scheduleState
    override val error: LiveData<String> = _error
    override val navigationAction: LiveData<ScheduleDetailNavigationAction> = _navigationAction

    init {
        dayNameUseCase()
            .let { _dayName.postValue(it) }
        getSchedule()
    }

    override fun getSchedule() {
        generateLoadingList()
            .let { PresentationScheduleState.Loading(it) }
            .let { _scheduleState.postValue(it) }

        scheduleUseCase()
            .subscribeOn(backgroundScheduler)
            .observeOn(backgroundScheduler)
            .subscribe(::onSchedule) { _error.postValue(unknownErrorMessage) }
            .addTo(compositeDisposable)
    }

    private fun onSchedule(
        state: ScheduleState
    ) {
        when (state) {
            is ScheduleState.Error -> _error.postValue(state.message)
            is ScheduleState.Success -> mapper.toPresentation(state.programList)
                .let { _scheduleState.postValue(it) }
        }
    }

    override fun onBack() {
        _navigationAction.postValue(Back)
    }

    private fun generateLoadingList(
    ): List<ProgramType.Loading> = mutableListOf<ProgramType.Loading>()
        .let { for (i in 0..15) it.add(ProgramType.Loading); it }

}
