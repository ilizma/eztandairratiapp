package com.ilizma.menu.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableSharedFlow

class MenuScreenViewModelFactory(
    private val menuScreenViewModelAssistedFactory: MenuScreenViewModelAssistedFactory,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = menuScreenViewModelAssistedFactory.create(
        _navigationAction = MutableSharedFlow(),
    ) as T

}