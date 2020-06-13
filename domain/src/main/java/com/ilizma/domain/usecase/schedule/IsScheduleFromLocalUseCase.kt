package com.ilizma.domain.usecase.schedule

import com.ilizma.domain.repository.ScheduleRepository
import com.ilizma.domain.usecase.base.CompletableUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class IsScheduleFromLocalUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : CompletableUseCase<Unit> {

    override fun invoke(params: Unit): Completable =
        scheduleRepository.isScheduleFromLocal()

}