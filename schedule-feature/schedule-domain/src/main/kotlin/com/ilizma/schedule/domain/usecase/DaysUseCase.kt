package com.ilizma.schedule.domain.usecase

import com.ilizma.schedule.domain.model.Days

interface DaysUseCase {

    operator fun invoke(): Days

}