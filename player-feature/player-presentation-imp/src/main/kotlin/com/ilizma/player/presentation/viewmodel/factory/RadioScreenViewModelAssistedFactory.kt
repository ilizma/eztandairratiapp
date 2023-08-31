package com.ilizma.player.presentation.viewmodel.factory

import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModelImp
import com.ilizma.presentation.SingleLiveEvent
import dagger.assisted.AssistedFactory
import io.reactivex.rxjava3.core.Scheduler

@AssistedFactory
interface RadioScreenViewModelAssistedFactory {

    fun create(
        mapper: PlayerStateMapper,
        backgroundScheduler: Scheduler,
        _navigationAction: SingleLiveEvent<RadioScreenNavigationAction>,
    ): RadioScreenViewModelImp

}