package com.ilizma.schedule.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ilizma.resources.Res
import com.ilizma.resources.unknown_error
import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.domain.usecase.DayNameUseCase
import com.ilizma.schedule.domain.usecase.SaveScheduleDetailArgsUseCase
import com.ilizma.schedule.domain.usecase.ScheduleUseCase
import com.ilizma.schedule.presentation.mapper.ScheduleStateMapper
import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction.Back
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import com.ilizma.schedule.presentation.model.ScheduleState as PresentationScheduleState

class ScheduleDetailScreenViewModelImp(
    private val saveCacheUseCase: SaveScheduleDetailArgsUseCase,
    private val dayNameUseCase: DayNameUseCase,
    private val scheduleUseCase: ScheduleUseCase,
    private val mapper: ScheduleStateMapper,
    private val isDebug: Boolean,
    private val _scheduleState: MutableSharedFlow<PresentationScheduleState>,
    private val _navigationAction: MutableSharedFlow<ScheduleDetailNavigationAction>,
) : ScheduleDetailScreenViewModel() {

    override val scheduleState: Flow<PresentationScheduleState> = _scheduleState
        .distinctUntilChanged()
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = generateLoadingList()
                .let { PresentationScheduleState.Loading(it) },
        )
    override val navigationAction: Flow<ScheduleDetailNavigationAction> = _navigationAction

    override fun saveCache(
        id: Int,
        name: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            saveCacheUseCase(id, name)
        }
    }

    override fun getSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                scheduleUseCase()
                    .let { onSchedule(dayNameUseCase(), it) }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    override fun retrySchedule() {
        generateLoadingList()
            .let { PresentationScheduleState.Loading(it) }
            .let { viewModelScope.launch(Dispatchers.IO) { _scheduleState.emit(it) } }

        getSchedule()
    }

    private fun onSchedule(
        title: String,
        state: ScheduleState,
    ) {
        mapper.from(
            title = title,
            state = state,
        ).let { viewModelScope.launch(Dispatchers.IO) { _scheduleState.emit(it) } }
    }

    private suspend fun onError(
        throwable: Throwable,
    ) {
        when (isDebug) {
            true -> throwable.message ?: getString(Res.string.unknown_error)
            false -> getString(Res.string.unknown_error)
        }.let { PresentationScheduleState.Error(it) }
            .let { viewModelScope.launch(Dispatchers.IO) { _scheduleState.emit(it) } }
    }

    override fun onBack() {
        viewModelScope.launch(Dispatchers.IO) { _navigationAction.emit(Back) }
    }

    private fun generateLoadingList(
    ): List<ProgramType.Loading> = mutableListOf<ProgramType.Loading>()
        .let { for (i in 0..15) it.add(ProgramType.Loading); it }

}
