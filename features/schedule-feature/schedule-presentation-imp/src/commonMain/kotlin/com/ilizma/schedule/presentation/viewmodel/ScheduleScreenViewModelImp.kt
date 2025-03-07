package com.ilizma.schedule.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ilizma.resources.Res
import com.ilizma.resources.days_array
import com.ilizma.schedule.presentation.mapper.DayMapper
import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.presentation.model.Days
import com.ilizma.schedule.presentation.model.ScheduleScreenIntent
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction.Back
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction.ScheduleDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getStringArray

class ScheduleScreenViewModelImp(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val mapper: DayMapper,
    private val _days: MutableStateFlow<Days>,
    private val _navigationAction: MutableSharedFlow<ScheduleScreenNavigationAction>,
) : ScheduleScreenViewModel() {

    init {
        viewModelScope.launch(dispatcher) {
            getStringArray(Res.array.days_array)
                .mapIndexed { index, day -> mapper.from(index, day) }
                .let { Days(it) }
                .let { _days.emit(it) }
        }
    }

    override val days: Flow<Days> = _days
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = Days(listOf()),
        )
    override val navigationAction: Flow<ScheduleScreenNavigationAction> = _navigationAction

    override fun onIntent(
        intent: ScheduleScreenIntent,
    ) {
        when (intent) {
            is ScheduleScreenIntent.Click -> onClick(
                day = intent.day,
            )

            ScheduleScreenIntent.Back -> onBack()
        }
    }

    private fun onClick(day: Day) {
        viewModelScope.launch(dispatcher) {
            ScheduleDetail(day)
                .let { _navigationAction.emit(it) }
        }
    }

    private fun onBack() {
        viewModelScope.launch(dispatcher) { _navigationAction.emit(Back) }
    }

}
