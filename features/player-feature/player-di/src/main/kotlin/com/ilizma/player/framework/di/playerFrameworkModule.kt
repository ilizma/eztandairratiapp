package com.ilizma.player.framework.di

import android.content.ComponentName
import androidx.media3.common.util.UnstableApi
import com.ilizma.player.framework.PlayerFramework
import com.ilizma.player.framework.PlayerFrameworkImp
import com.ilizma.player.framework.model.PlayerState
import com.ilizma.player.framework.service.MusicService
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

@UnstableApi
val playerFrameworkModule: Module = module {

    single<PlayerFramework> {
        ComponentName(
            androidContext(),
            MusicService::class.java,
        ).let {
            PlayerFrameworkImp(
                context = androidContext(),
                serviceComponent = it,
                _playerState = MutableStateFlow(PlayerState.Stopped),
            )
        }
    }

}