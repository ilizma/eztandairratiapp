package com.ilizma.schedule.domain.usecase

import com.ilizma.schedule.domain.repository.DayNameRepository

class DayNameUseCase(
    private val repository: DayNameRepository,
) {

    operator fun invoke(
    ): String = repository.get()

}