package com.ilizma.navigation.domain.usecase

import com.ilizma.navigation.domain.model.ScheduleDetailArgs

interface SaveScheduleDetailArgsUseCase {

    operator fun invoke(args: ScheduleDetailArgs)

}