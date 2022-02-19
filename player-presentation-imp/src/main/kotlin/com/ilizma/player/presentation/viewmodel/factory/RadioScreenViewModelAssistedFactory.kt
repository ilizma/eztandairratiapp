package com.ilizma.player.presentation.viewmodel.factory

import androidx.lifecycle.MutableLiveData
import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.player.presentation.model.PlayerState
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModelImp
import com.ilizma.presentation.SingleLiveEvent
import dagger.assisted.AssistedFactory
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable

@AssistedFactory
interface RadioScreenViewModelAssistedFactory {

    fun create(
        mapper: PlayerStateMapper,
        backgroundScheduler: Scheduler,
        compositeDisposable: CompositeDisposable,
        _playerState: MutableLiveData<PlayerState>,
        _navigationAction: SingleLiveEvent<RadioScreenNavigationAction>,
    ): RadioScreenViewModelImp

}