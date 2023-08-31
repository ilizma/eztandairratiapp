package com.ilizma.navigation.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ilizma.navigation.presentation.mapper.ScheduleDetailArgsMapper

class NavigationScreenViewModelFactory(
    private val navigationScreenViewModelAssistedFactory: NavigationScreenViewModelAssistedFactory,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = navigationScreenViewModelAssistedFactory.create(
        mapper = ScheduleDetailArgsMapper()
    ) as T

}