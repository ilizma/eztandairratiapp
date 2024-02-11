package com.ilizma.schedule.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ilizma.schedule.domain.usecase.DaysUseCase
import com.ilizma.schedule.presentation.mapper.DaysMapper
import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.presentation.model.Days
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction.Back
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction.ScheduleDetail
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ScheduleScreenViewModelImp @AssistedInject constructor(
    useCase: DaysUseCase,
    @Assisted private val mapper: DaysMapper,
    @Assisted private val _navigationAction: MutableSharedFlow<ScheduleScreenNavigationAction>,
) : ScheduleScreenViewModel() {

    override val days: Flow<Days> = flowOf(useCase())
        .flowOn(Dispatchers.IO)
        .distinctUntilChanged()
        .map { mapper.toPresentation(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = Days(listOf()),
        )
    override val navigationAction: Flow<ScheduleScreenNavigationAction> = _navigationAction

    override fun onClick(day: Day) {
        ScheduleDetail(day)
            .let { viewModelScope.launch(Dispatchers.IO) { _navigationAction.emit(it) } }
    }

    override fun onBack() {
        viewModelScope.launch(Dispatchers.IO) { _navigationAction.emit(Back) }
    }

}
