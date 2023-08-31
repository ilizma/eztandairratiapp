package com.ilizma.menu.presentation.viewmodel.factory

import com.ilizma.menu.presentation.model.MenuNavigationAction
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModelImp
import com.ilizma.presentation.SingleLiveEvent
import dagger.assisted.AssistedFactory

@AssistedFactory
interface MenuScreenViewModelAssistedFactory {

    fun create(
        _navigationAction: SingleLiveEvent<MenuNavigationAction>,
    ): MenuScreenViewModelImp

}