package com.ilizma.player.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.domain.usecase.PlayerPlayUseCase
import com.ilizma.player.domain.usecase.PlayerStateUseCase
import com.ilizma.player.domain.usecase.PlayerStopUseCase
import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.player.presentation.model.RadioScreenIntent
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import com.ilizma.player.presentation.model.RadioScreenNavigationAction.Back
import kotlinx.coroutines.CoroutineDispatcher
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
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    stateUseCase: PlayerStateUseCase,
    private val playUseCase: PlayerPlayUseCase,
    private val stopUseCase: PlayerStopUseCase,
    private val mapper: PlayerStateMapper,
    private val _navigationAction: MutableSharedFlow<RadioScreenNavigationAction>,
) : RadioScreenViewModel() {

    override val playerState: Flow<PresentationPlayerState> = stateUseCase()
        .map(::onPlayerState)
        .flowOn(ioDispatcher)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = PresentationPlayerState.Stopped,
        )

    override val navigationAction: Flow<RadioScreenNavigationAction> = _navigationAction

    override fun onIntent(
        intent: RadioScreenIntent,
    ) {
        when (intent) {
            RadioScreenIntent.Play -> onPlay()
            RadioScreenIntent.Stop -> onStop()
            RadioScreenIntent.Back -> onBack()
        }
    }

    private fun onPlay() {
        viewModelScope.launch(mainDispatcher) { playUseCase() }
    }

    private fun onStop() {
        viewModelScope.launch(mainDispatcher) { stopUseCase() }
    }

    private fun onBack() {
        viewModelScope.launch(ioDispatcher) { _navigationAction.emit(Back) }
    }

    private fun onPlayerState(
        state: PlayerState,
    ): PresentationPlayerState = mapper.from(state)

}
