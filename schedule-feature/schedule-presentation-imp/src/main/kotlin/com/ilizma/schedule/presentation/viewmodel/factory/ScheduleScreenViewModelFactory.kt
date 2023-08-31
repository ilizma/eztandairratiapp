package com.ilizma.schedule.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ilizma.presentation.SingleLiveEvent
import com.ilizma.schedule.presentation.mapper.DayListMapper
import com.ilizma.schedule.presentation.mapper.DayMapper
import com.ilizma.schedule.presentation.mapper.DaysMapper

class ScheduleScreenViewModelFactory(
    private val scheduleScreenViewModelAssistedFactory: ScheduleScreenViewModelAssistedFactory,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = scheduleScreenViewModelAssistedFactory.create(
        mapper = DaysMapper(DayListMapper(DayMapper())),
        _navigationAction = SingleLiveEvent(),
    ) as T

}