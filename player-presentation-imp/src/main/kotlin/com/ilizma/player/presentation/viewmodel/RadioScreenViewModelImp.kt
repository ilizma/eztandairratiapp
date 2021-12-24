package com.ilizma.player.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilizma.player.domain.usecase.PlayerUseCase
import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.presentation.model.PlayerState as PresentationPlayerState
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import com.ilizma.player.presentation.model.RadioScreenNavigationAction.Back
import com.ilizma.presentation.SingleLiveEvent
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo

class RadioScreenViewModelImp(
    private val useCase: PlayerUseCase,
    private val mapper: PlayerStateMapper,
    backgroundScheduler: Scheduler,
    compositeDisposable: CompositeDisposable,
    private val _playerState: MutableLiveData<PresentationPlayerState>,
    private val _navigationAction: SingleLiveEvent<RadioScreenNavigationAction>,
) : RadioScreenViewModel() {

    override val playerState: LiveData<PresentationPlayerState> = _playerState
    override val navigationAction: LiveData<RadioScreenNavigationAction> = _navigationAction

    init {
        useCase.getState()
            .subscribeOn(backgroundScheduler)
            .observeOn(backgroundScheduler)
            .subscribe(::onPlayerState)
            .addTo(compositeDisposable)
    }

    override fun onPlay() {
        useCase.play()
    }

    override fun onStop() {
        useCase.stop()
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
