package com.ilizma.player.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ilizma.player.presentation.model.PlayerState
import com.ilizma.player.presentation.model.RadioScreenIntent
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import kotlinx.coroutines.flow.Flow

abstract class RadioScreenViewModel : ViewModel() {

    abstract val playerState: Flow<PlayerState>

    abstract val navigationAction: Flow<RadioScreenNavigationAction>

    abstract fun onIntent(intent: RadioScreenIntent)

    /*abstract fun setUpMediaRouteButton(
        menu: Menu,
        menuResourceId: Int,
    )*/

}