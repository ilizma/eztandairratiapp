package com.ilizma.schedule.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilizma.presentation.SingleLiveEvent
import com.ilizma.schedule.domain.usecase.DaysUseCase
import com.ilizma.schedule.presentation.mapper.DaysMapper
import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.presentation.model.Days
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction.Back
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction.ScheduleDetail

class ScheduleScreenViewModelImp(
    useCase: DaysUseCase,
    private val mapper: DaysMapper,
    private val _days: MutableLiveData<Days>,
    private val _navigationAction: SingleLiveEvent<ScheduleScreenNavigationAction>,
) : ScheduleScreenViewModel() {

    override val days: LiveData<Days> = _days
    override val navigationAction: LiveData<ScheduleScreenNavigationAction> = _navigationAction

    init {
        useCase()
            .let { mapper.toPresentation(it) }
            .let { _days.postValue(it) }
    }

    override fun onClick(day: Day) {
        ScheduleDetail(day)
            .let { _navigationAction.postValue(it) }
    }

    override fun onBack() {
        _navigationAction.postValue(Back)
    }

}
