package com.ilizma.domain.usecase.schedule

import com.ilizma.domain.entity.schedule.Schedule
import com.ilizma.domain.repository.ScheduleRepository
import com.ilizma.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetScheduleFromLocalUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : SingleUseCase<List<Schedule>, GetScheduleFromLocalParams> {

    override fun invoke(params: GetScheduleFromLocalParams): Single<List<Schedule>> =
        with(params) {
            scheduleRepository.getScheduleFromLocal(day)
        }

}

inline class GetScheduleFromLocalParams(val day: Int)