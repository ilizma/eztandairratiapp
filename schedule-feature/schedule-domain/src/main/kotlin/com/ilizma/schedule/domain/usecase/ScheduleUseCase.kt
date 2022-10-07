package com.ilizma.schedule.domain.usecase

import com.ilizma.schedule.domain.model.ScheduleState
import io.reactivex.rxjava3.core.Single

interface ScheduleUseCase {

    operator fun invoke() : Single<ScheduleState>

}