package com.ilizma.schedule.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ilizma.schedule.presentation.mapper.ProgramTypeMapper
import com.ilizma.schedule.presentation.mapper.ScheduleStateMapper
import kotlinx.coroutines.flow.MutableSharedFlow

class ScheduleDetailViewModelFactory(
    private val scheduleDetailViewModelAssistedFactory: ScheduleDetailViewModelAssistedFactory,
    private val unknownErrorMessage: String,
    private val isDebug: Boolean,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = scheduleDetailViewModelAssistedFactory.create(
        mapper = ScheduleStateMapper(ProgramTypeMapper()),
        unknownErrorMessage = unknownErrorMessage,
        isDebug = isDebug,
        _scheduleState = MutableSharedFlow(),
        _navigationAction = MutableSharedFlow(),
    ) as T

}