package com.ilizma.player.presentation.viewmodel

import android.view.Menu
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ilizma.player.presentation.model.PlayerState
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import kotlinx.coroutines.flow.Flow

abstract class RadioScreenViewModel : ViewModel() {

    abstract val playerState: Flow<PlayerState>

    abstract val navigationAction: LiveData<RadioScreenNavigationAction>

    abstract fun onPlay()

    abstract fun onStop()

    abstract fun onBack()

    abstract fun setUpMediaRouteButton(
        menu: Menu,
        menuResourceId: Int,
    )

}