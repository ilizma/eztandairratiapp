package com.ilizma.schedule.domain.usecase

import com.ilizma.schedule.domain.repository.ScheduleDetailArgsRepository

class SaveScheduleDetailArgsUseCase(
    private val repository: ScheduleDetailArgsRepository,
) {

    operator fun invoke(
        id: Int,
        name: String,
    ) {
        repository.save(
            id = id,
            name = name,
        )
    }
}