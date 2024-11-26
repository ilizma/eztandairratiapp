package com.ilizma.player.framework.di

import com.ilizma.player.framework.PlayerFramework
import com.ilizma.player.framework.model.PlayerState
import com.ilizma.player.framework.PlayerFrameworkImp
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.module.Module
import org.koin.dsl.module

actual val playerFrameworkModule: Module = module {

    single<PlayerFramework> {
        PlayerFrameworkImp(
            musicHelper = get(),
            _playerState = MutableStateFlow(PlayerState.Stopped),
        )
    }

}