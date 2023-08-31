package com.ilizma.navigation.presentation.viewmodel

import com.ilizma.navigation.domain.usecase.SaveScheduleDetailArgsUseCase
import com.ilizma.navigation.presentation.mapper.ScheduleDetailArgsMapper
import com.ilizma.navigation.presentation.model.ScheduleDetailArgs
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class NavigationScreenViewModelImp @AssistedInject constructor(
    val useCase: SaveScheduleDetailArgsUseCase,
    @Assisted val mapper: ScheduleDetailArgsMapper,
) : NavigationScreenViewModel() {

    override fun saveArgs(
        args: ScheduleDetailArgs,
    ) {
        mapper.from(args)
            .let { useCase(it) }
    }

}
