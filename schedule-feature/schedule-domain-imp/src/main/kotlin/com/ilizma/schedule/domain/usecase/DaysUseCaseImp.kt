package com.ilizma.schedule.domain.usecase

import com.ilizma.schedule.domain.model.Days
import com.ilizma.schedule.domain.repository.DaysRepository

class DaysUseCaseImp(
    private val repository: DaysRepository,
) : DaysUseCase {

    override fun invoke(): Days = repository.get()

}