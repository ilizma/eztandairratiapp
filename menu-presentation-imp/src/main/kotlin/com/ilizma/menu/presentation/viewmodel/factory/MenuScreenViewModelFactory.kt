package com.ilizma.menu.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ilizma.presentation.SingleLiveEvent

class MenuScreenViewModelFactory(
    private val menuScreenViewModelAssistedFactory: MenuScreenViewModelAssistedFactory,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = menuScreenViewModelAssistedFactory.create(
        _navigationAction = SingleLiveEvent(),
    ) as T

}