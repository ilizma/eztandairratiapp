package com.ilizma.player.presentation.viewmodel

import android.view.Menu
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilizma.cast.framework.CastFramework
import com.ilizma.cast.framework.model.CastState
import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.domain.usecase.PlayerPlayUseCase
import com.ilizma.player.domain.usecase.PlayerStateUseCase
import com.ilizma.player.domain.usecase.PlayerStopUseCase
import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import com.ilizma.player.presentation.model.RadioScreenNavigationAction.Back
import com.ilizma.player.presentation.model.RadioScreenNavigationAction.CastPlayer
import com.ilizma.presentation.SingleLiveEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import com.ilizma.player.presentation.model.PlayerState as PresentationPlayerState

class RadioScreenViewModelImp @AssistedInject constructor(
    private val stateUseCase: PlayerStateUseCase,
    private val playUseCase: PlayerPlayUseCase,
    private val stopUseCase: PlayerStopUseCase,
    private val castFramework: CastFramework,
    @Assisted private val mapper: PlayerStateMapper,
    @Assisted private val backgroundScheduler: Scheduler,
    @Assisted private val compositeDisposable: CompositeDisposable,
    @Assisted private val _playerState: MutableLiveData<PresentationPlayerState>,
    @Assisted private val _navigationAction: SingleLiveEvent<RadioScreenNavigationAction>,
) : RadioScreenViewModel(), DefaultLifecycleObserver {

    override val playerState: LiveData<PresentationPlayerState> = _playerState
    override val navigationAction: LiveData<RadioScreenNavigationAction> = _navigationAction

    init {
        castFramework.init()
        getState()
    }

    override fun getState() {
        stateUseCase()
            .subscribeOn(backgroundScheduler)
            .observeOn(backgroundScheduler)
            .subscribe(::onPlayerState)
            .addTo(compositeDisposable)
    }

    override fun onPlay() {
        when (castFramework.castState.blockingFirst()) {
            CastState.CONNECTED -> _navigationAction.postValue(CastPlayer)
            CastState.DISCONNECTED -> playUseCase()
        }
    }

    override fun onStop() {
        stopUseCase()
    }

    override fun onBack() {
        _navigationAction.postValue(Back)
    }

    override fun setUpMediaRouteButton(
        menu: Menu,
        menuResourceId: Int,
    ) {
        castFramework.setUpMediaRouteButton(
            menu = menu,
            menuResourceId = menuResourceId,
        )
    }

    private fun onPlayerState(
        state: PlayerState
    ) {
        mapper.toPresentation(state)
            .let { _playerState.postValue(it) }
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        castFramework.onResume()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        castFramework.onDestroy()
    }

}
