package com.ilizma.schedule.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ilizma.schedule.presentation.mapper.DayListMapper
import com.ilizma.schedule.presentation.mapper.DayMapper
import com.ilizma.schedule.presentation.mapper.DaysMapper
import kotlinx.coroutines.flow.MutableSharedFlow

class ScheduleScreenViewModelFactory(
    private val scheduleScreenViewModelAssistedFactory: ScheduleScreenViewModelAssistedFactory,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = scheduleScreenViewModelAssistedFactory.create(
        mapper = DaysMapper(DayListMapper(DayMapper())),
        _navigationAction = MutableSharedFlow(),
    ) as T

}