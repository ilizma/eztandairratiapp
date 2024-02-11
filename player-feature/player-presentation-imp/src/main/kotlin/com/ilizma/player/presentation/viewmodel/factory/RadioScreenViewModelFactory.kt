package com.ilizma.player.presentation.viewmodel.factory

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ilizma.player.presentation.mapper.PlayerStateMapper
import kotlinx.coroutines.flow.MutableSharedFlow

class RadioScreenViewModelFactory(
    private val activity: Activity,
    private val radioScreenViewModelAssistedFactory: RadioScreenViewModelAssistedFactory,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = radioScreenViewModelAssistedFactory.create(
        mapper = PlayerStateMapper(),
        _navigationAction = MutableSharedFlow(),
    ).also { (activity as ComponentActivity).lifecycle.addObserver(it) } as T

}