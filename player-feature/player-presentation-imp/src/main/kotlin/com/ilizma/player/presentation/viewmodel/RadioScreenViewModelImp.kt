package com.ilizma.player.presentation.viewmodel

import android.view.Menu
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.rx3.asFlow
import com.ilizma.player.presentation.model.PlayerState as PresentationPlayerState

class RadioScreenViewModelImp @AssistedInject constructor(
    stateUseCase: PlayerStateUseCase,
    private val playUseCase: PlayerPlayUseCase,
    private val stopUseCase: PlayerStopUseCase,
    private val castFramework: CastFramework,
    @Assisted private val mapper: PlayerStateMapper,
    @Assisted private val backgroundScheduler: Scheduler,
    @Assisted private val _navigationAction: SingleLiveEvent<RadioScreenNavigationAction>,
) : RadioScreenViewModel(), DefaultLifecycleObserver {

    override val playerState: Flow<PresentationPlayerState> = stateUseCase()
        .subscribeOn(backgroundScheduler)
        .observeOn(backgroundScheduler)
        .asFlow()
        .flowOn(Dispatchers.IO)
        .map(::onPlayerState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = PresentationPlayerState.Stopped,
        )

    override val navigationAction: LiveData<RadioScreenNavigationAction> = _navigationAction

    init {
        castFramework.init()
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
