package com.ilizma.player.presentation.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.ilizma.cast.framework.CastFramework
import com.ilizma.cast.framework.model.CastState
import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.domain.usecase.PlayerPlayUseCase
import com.ilizma.player.domain.usecase.PlayerStateUseCase
import com.ilizma.player.domain.usecase.PlayerStopUseCase
import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.player.presentation.model.RadioScreenIntent
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import com.ilizma.player.presentation.model.RadioScreenNavigationAction.Back
import com.ilizma.player.presentation.model.RadioScreenNavigationAction.CastPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.ilizma.player.presentation.model.PlayerState as PresentationPlayerState

class RadioScreenViewModelImp(
    stateUseCase: PlayerStateUseCase,
    private val playUseCase: PlayerPlayUseCase,
    private val stopUseCase: PlayerStopUseCase,
    private val castFramework: CastFramework,
    private val mapper: PlayerStateMapper,
    private val _navigationAction: MutableSharedFlow<RadioScreenNavigationAction>,
) : RadioScreenViewModel(), DefaultLifecycleObserver {

    override val playerState: Flow<PresentationPlayerState> = stateUseCase()
        .map(::onPlayerState)
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = PresentationPlayerState.Stopped,
        )

    override val navigationAction: Flow<RadioScreenNavigationAction> = _navigationAction

    init {
        castFramework.init()
    }

    override fun onIntent(intent: RadioScreenIntent) {
        when (intent) {
            RadioScreenIntent.Play -> onPlay()
            RadioScreenIntent.Stop -> onStop()
            RadioScreenIntent.Back -> onBack()
        }
    }

    private fun onPlay() {
        viewModelScope.launch(Dispatchers.IO) {
            when (castFramework.castState.first()) {
                CastState.CONNECTED -> _navigationAction.emit(CastPlayer)
                CastState.DISCONNECTED -> viewModelScope.launch(Dispatchers.Main) { playUseCase() }
            }
        }
    }

    private fun onStop() {
        stopUseCase()
    }

    private fun onBack() {
        viewModelScope.launch(Dispatchers.IO) { _navigationAction.emit(Back) }
    }

    /*override fun setUpMediaRouteButton(
        menu: Menu,
        menuResourceId: Int,
    ) {
        castFramework.setUpMediaRouteButton(
            menu = menu,
            menuResourceId = menuResourceId,
        )
    }*/

    private fun onPlayerState(
        state: PlayerState,
    ): PresentationPlayerState = mapper.toPresentation(state)


    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        castFramework.onResume()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        castFramework.onDestroy()
    }

}
