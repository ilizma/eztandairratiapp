package com.ilizma.menu.presentation.viewmodel.factory

import com.ilizma.menu.presentation.model.MenuNavigationAction
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModelImp
import dagger.assisted.AssistedFactory
import kotlinx.coroutines.flow.MutableSharedFlow

@AssistedFactory
interface MenuScreenViewModelAssistedFactory {

    fun create(
        _navigationAction: MutableSharedFlow<MenuNavigationAction>,
    ): MenuScreenViewModelImp

}