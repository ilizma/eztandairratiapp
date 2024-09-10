package com.ilizma.schedule.domain.usecase

import com.ilizma.schedule.domain.repository.ScheduleDetailArgsRepository

class SaveScheduleDetailArgsUseCaseImp(
    private val repository: ScheduleDetailArgsRepository,
) : SaveScheduleDetailArgsUseCase {

    override fun invoke(id: Int, name: String) {
        repository.save(id, name)
    }
}