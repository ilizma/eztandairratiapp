package com.ilizma.schedule.domain.usecase

interface SaveScheduleDetailArgsUseCase {

    operator fun invoke(id: Int, name: String)

}