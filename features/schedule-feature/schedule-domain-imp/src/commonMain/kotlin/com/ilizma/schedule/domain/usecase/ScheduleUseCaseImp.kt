package com.ilizma.schedule.domain.usecase

import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.domain.repository.ScheduleRepository

class ScheduleUseCaseImp(
    private val repository: ScheduleRepository,
) : ScheduleUseCase {

    override suspend fun invoke(): ScheduleState = repository.get()

}