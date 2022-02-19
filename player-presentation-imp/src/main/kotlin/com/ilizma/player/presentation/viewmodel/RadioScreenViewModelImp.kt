package com.ilizma.player.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.domain.usecase.PlayerPlayUseCase
import com.ilizma.player.domain.usecase.PlayerStateUseCase
import com.ilizma.player.domain.usecase.PlayerStopUseCase
import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import com.ilizma.player.presentation.model.RadioScreenNavigationAction.Back
import com.ilizma.presentation.SingleLiveEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import com.ilizma.player.presentation.model.PlayerState as PresentationPlayerState

class RadioScreenViewModelImp @AssistedInject constructor(
    stateUseCase: PlayerStateUseCase,
    private val playUseCase: PlayerPlayUseCase,
    private val stopUseCase: PlayerStopUseCase,
    @Assisted private val mapper: PlayerStateMapper,
    @Assisted backgroundScheduler: Scheduler,
    @Assisted compositeDisposable: CompositeDisposable,
    @Assisted private val _playerState: MutableLiveData<PresentationPlayerState>,
    @Assisted private val _navigationAction: SingleLiveEvent<RadioScreenNavigationAction>,
) : RadioScreenViewModel() {

    override val playerState: LiveData<PresentationPlayerState> = _playerState
    override val navigationAction: LiveData<RadioScreenNavigationAction> = _navigationAction

    init {
        stateUseCase()
            .subscribeOn(backgroundScheduler)
            .observeOn(backgroundScheduler)
            .subscribe(::onPlayerState)
            .addTo(compositeDisposable)
    }

    override fun onPlay() {
        playUseCase()
    }

    override fun onStop() {
        stopUseCase()
    }

    override fun onBack() {
        _navigationAction.postValue(Back)
    }

    private fun onPlayerState(
        state: PlayerState
    ) {
        mapper.toPresentation(state)
            .let { _playerState.postValue(it) }
    }

}
