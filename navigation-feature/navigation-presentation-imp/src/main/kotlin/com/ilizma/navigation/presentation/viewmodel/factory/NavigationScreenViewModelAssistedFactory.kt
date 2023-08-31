package com.ilizma.navigation.presentation.viewmodel.factory

import com.ilizma.navigation.presentation.mapper.ScheduleDetailArgsMapper
import com.ilizma.navigation.presentation.viewmodel.NavigationScreenViewModelImp
import dagger.assisted.AssistedFactory

@AssistedFactory
interface NavigationScreenViewModelAssistedFactory {

    fun create(
        mapper: ScheduleDetailArgsMapper,
    ): NavigationScreenViewModelImp

}