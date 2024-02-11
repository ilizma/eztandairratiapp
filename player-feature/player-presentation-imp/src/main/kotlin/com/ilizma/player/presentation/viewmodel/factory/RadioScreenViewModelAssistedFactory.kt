package com.ilizma.player.presentation.viewmodel.factory

import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModelImp
import dagger.assisted.AssistedFactory
import kotlinx.coroutines.flow.MutableSharedFlow

@AssistedFactory
interface RadioScreenViewModelAssistedFactory {

    fun create(
        mapper: PlayerStateMapper,
        _navigationAction: MutableSharedFlow<RadioScreenNavigationAction>,
    ): RadioScreenViewModelImp

}