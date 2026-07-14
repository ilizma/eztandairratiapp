package com.ilizma.schedule.domain.usecase

import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.domain.repository.ScheduleRepository

class ScheduleUseCase(
    private val repository: ScheduleRepository,
) {

    suspend operator fun invoke(
    ): ScheduleState = repository.get()

}