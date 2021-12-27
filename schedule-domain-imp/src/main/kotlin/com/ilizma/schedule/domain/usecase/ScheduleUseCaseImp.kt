package com.ilizma.schedule.domain.usecase

import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.domain.repository.ScheduleRepository
import io.reactivex.rxjava3.core.Single

class ScheduleUseCaseImp(
    private val repository: ScheduleRepository
) : ScheduleUseCase {

    override fun invoke(): Single<ScheduleState> = repository.get()

}