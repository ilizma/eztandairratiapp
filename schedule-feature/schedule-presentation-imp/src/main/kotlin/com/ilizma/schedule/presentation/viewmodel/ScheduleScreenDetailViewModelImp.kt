package com.ilizma.schedule.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ilizma.presentation.SingleLiveEvent
import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.domain.usecase.DayNameUseCase
import com.ilizma.schedule.domain.usecase.ScheduleUseCase
import com.ilizma.schedule.presentation.mapper.ScheduleStateMapper
import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction.Back
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.ilizma.schedule.presentation.model.ScheduleState as PresentationScheduleState

class ScheduleScreenDetailViewModelImp @AssistedInject constructor(
    private val dayNameUseCase: DayNameUseCase,
    private val scheduleUseCase: ScheduleUseCase,
    @Assisted private val mapper: ScheduleStateMapper,
    @Assisted private val unknownErrorMessage: String,
    @Assisted private val isDebug: Boolean,
    @Assisted private val _dayName: MutableSharedFlow<String>,
    @Assisted private val _scheduleState: MutableSharedFlow<PresentationScheduleState>,
    @Assisted private val _navigationAction: SingleLiveEvent<ScheduleDetailNavigationAction>,
) : ScheduleScreenDetailViewModel() {

    override val dayName: Flow<String> = _dayName
        .distinctUntilChanged()
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = "",
        )
    override val scheduleState: Flow<PresentationScheduleState> = _scheduleState
        .distinctUntilChanged()
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = generateLoadingList()
                .let { PresentationScheduleState.Loading(it) },
        )
    override val navigationAction: LiveData<ScheduleDetailNavigationAction> = _navigationAction

    override fun getTitle() {
        dayNameUseCase()
            .let { viewModelScope.launch { _dayName.emit(it) } }
    }

    override fun getSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                scheduleUseCase()
                    .let { onSchedule(it) }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    override fun retrySchedule() {
        generateLoadingList()
            .let { PresentationScheduleState.Loading(it) }
            .let { viewModelScope.launch { _scheduleState.emit(it) } }

        getSchedule()
    }

    private fun onSchedule(
        state: ScheduleState,
    ) {
        mapper.from(state)
            .let { viewModelScope.launch { _scheduleState.emit(it) } }
    }

    private fun onError(
        throwable: Throwable,
    ) {
        when (isDebug) {
            true -> throwable.message ?: unknownErrorMessage
            false -> unknownErrorMessage
        }.let { PresentationScheduleState.Error(it) }
            .let { viewModelScope.launch { _scheduleState.emit(it) } }
    }

    override fun onBack() {
        _navigationAction.postValue(Back)
    }

    private fun generateLoadingList(
    ): List<ProgramType.Loading> = mutableListOf<ProgramType.Loading>()
        .let { for (i in 0..15) it.add(ProgramType.Loading); it }

}
