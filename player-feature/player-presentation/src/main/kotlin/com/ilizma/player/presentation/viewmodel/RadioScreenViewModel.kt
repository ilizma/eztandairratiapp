package com.ilizma.player.presentation.viewmodel

import android.view.Menu
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ilizma.player.presentation.model.PlayerState
import com.ilizma.player.presentation.model.RadioScreenNavigationAction

abstract class RadioScreenViewModel : ViewModel() {

    abstract val playerState: LiveData<PlayerState>

    abstract val navigationAction: LiveData<RadioScreenNavigationAction>

    abstract fun getState()

    abstract fun onPlay()

    abstract fun onStop()

    abstract fun onBack()

    abstract fun setUpMediaRouteButton(
        menu: Menu,
        menuResourceId: Int,
    )

}