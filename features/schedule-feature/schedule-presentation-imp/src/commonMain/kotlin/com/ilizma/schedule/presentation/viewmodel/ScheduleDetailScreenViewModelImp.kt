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
import com.ilizma.schedule.presentation.model.ScheduleDetailScreenIntent
import kotlinx.coroutines.CoroutineDispatcher
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
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
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
        .flowOn(dispatcher)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = generateLoadingList()
                .let { PresentationScheduleState.Loading(it) },
        )
    override val navigationAction: Flow<ScheduleDetailNavigationAction> = _navigationAction

    override fun onIntent(
        intent: ScheduleDetailScreenIntent,
    ) {
        when (intent) {
            is ScheduleDetailScreenIntent.SaveCache -> saveCache(
                id = intent.id,
                name = intent.name,
            )

            ScheduleDetailScreenIntent.GetSchedule -> getSchedule()
            ScheduleDetailScreenIntent.RetrySchedule -> retrySchedule()
            ScheduleDetailScreenIntent.Back -> onBack()
        }
    }

    private fun saveCache(
        id: Int,
        name: String,
    ) {
        viewModelScope.launch(dispatcher) { saveCacheUseCase(id, name) }
    }

    private fun getSchedule() {
        viewModelScope.launch(dispatcher) {
            try {
                scheduleUseCase()
                    .let { onSchedule(dayNameUseCase(), it) }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    private fun retrySchedule() {
        generateLoadingList()
            .let { PresentationScheduleState.Loading(it) }
            .let { viewModelScope.launch(dispatcher) { _scheduleState.emit(it) } }

        getSchedule()
    }

    private fun onSchedule(
        title: String,
        state: ScheduleState,
    ) {
        mapper.from(
            title = title,
            state = state,
        ).let { viewModelScope.launch(dispatcher) { _scheduleState.emit(it) } }
    }

    private suspend fun onError(
        throwable: Throwable,
    ) {
        when (isDebug) {
            true -> throwable.message ?: getString(Res.string.unknown_error)
            false -> getString(Res.string.unknown_error)
        }.let { PresentationScheduleState.Error(it) }
            .let { _scheduleState.emit(it) }
    }

    private fun onBack() {
        viewModelScope.launch(dispatcher) { _navigationAction.emit(Back) }
    }

    private fun generateLoadingList(
    ): List<ProgramType.Loading> = mutableListOf<ProgramType.Loading>()
        .let { for (i in 0..15) it.add(ProgramType.Loading); it }

}
