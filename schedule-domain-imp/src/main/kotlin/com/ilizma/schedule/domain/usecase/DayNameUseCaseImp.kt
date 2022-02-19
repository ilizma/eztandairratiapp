package com.ilizma.schedule.domain.usecase

import com.ilizma.schedule.domain.repository.DayNameRepository

class DayNameUseCaseImp(
    private val repository: DayNameRepository,
) : DayNameUseCase {

    override fun invoke(): String = repository.get()

}