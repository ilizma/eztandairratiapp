package com.ilizma.domain.usecase.schedule

import com.ilizma.domain.repository.ScheduleRepository
import com.ilizma.domain.usecase.base.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class GetScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : CompletableUseCase<Unit> {

    override fun invoke(params: Unit): Completable =
        scheduleRepository.getSchedule()

}