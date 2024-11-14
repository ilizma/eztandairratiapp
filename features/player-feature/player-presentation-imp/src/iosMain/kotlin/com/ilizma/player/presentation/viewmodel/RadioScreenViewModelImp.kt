package com.ilizma.player.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.domain.usecase.PlayerPlayUseCase
import com.ilizma.player.domain.usecase.PlayerStateUseCase
import com.ilizma.player.domain.usecase.PlayerStopUseCase
import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import com.ilizma.player.presentation.model.RadioScreenNavigationAction.Back
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.ilizma.player.presentation.model.PlayerState as PresentationPlayerState

class RadioScreenViewModelImp(
    stateUseCase: PlayerStateUseCase,
    private val playUseCase: PlayerPlayUseCase,
    private val stopUseCase: PlayerStopUseCase,
    private val mapper: PlayerStateMapper,
    private val _navigationAction: MutableSharedFlow<RadioScreenNavigationAction>,
) : RadioScreenViewModel() {

    override val playerState: Flow<PresentationPlayerState> = stateUseCase()
        .map(::onPlayerState)
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = PresentationPlayerState.Stopped,
        )

    override val navigationAction: Flow<RadioScreenNavigationAction> = _navigationAction

    override fun onPlay() {
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.launch(Dispatchers.Main) { playUseCase() }
        }
    }

    override fun onStop() {
        stopUseCase()
    }

    override fun onBack() {
        viewModelScope.launch(Dispatchers.IO) { _navigationAction.emit(Back) }
    }

    private fun onPlayerState(
        state: PlayerState,
    ): PresentationPlayerState = mapper.toPresentation(state)

}
