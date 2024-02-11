package com.ilizma.player.presentation.viewmodel.factory

import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModelImp
import com.ilizma.presentation.SingleLiveEvent
import dagger.assisted.AssistedFactory

@AssistedFactory
interface RadioScreenViewModelAssistedFactory {

    fun create(
        mapper: PlayerStateMapper,
        _navigationAction: SingleLiveEvent<RadioScreenNavigationAction>,
    ): RadioScreenViewModelImp

}