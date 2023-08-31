package com.ilizma.navigation.domain.usecase

import com.ilizma.navigation.domain.model.ScheduleDetailArgs
import com.ilizma.navigation.domain.repository.ScheduleDetailRepository

class SaveScheduleDetailArgsUseCaseImp(
    private val repository: ScheduleDetailRepository,
) : SaveScheduleDetailArgsUseCase {

    override fun invoke(args: ScheduleDetailArgs) {
        repository.saveScheduleDetailArgs(args)
    }

}