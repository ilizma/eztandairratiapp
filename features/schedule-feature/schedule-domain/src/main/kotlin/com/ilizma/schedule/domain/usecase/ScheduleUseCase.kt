package com.ilizma.schedule.domain.usecase

import com.ilizma.schedule.domain.model.ScheduleState

interface ScheduleUseCase {

    suspend operator fun invoke() : ScheduleState

}