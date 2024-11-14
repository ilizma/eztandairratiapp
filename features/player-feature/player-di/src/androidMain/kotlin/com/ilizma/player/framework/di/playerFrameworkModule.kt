package com.ilizma.player.framework.di

import android.content.ComponentName
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.ilizma.player.framework.PlayerFramework
import com.ilizma.player.framework.PlayerFrameworkImp
import com.ilizma.player.framework.model.PlayerState
import com.ilizma.player.framework.service.MusicService
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

@UnstableApi
actual val playerFrameworkModule: Module = module {

    single<PlayerFramework> {
        ComponentName(
            androidContext(),
            MusicService::class.java,
        ).let {
            SessionToken(
                androidContext(),
                it,
            )
        }.let {
            MediaController.Builder(
                androidContext(),
                it,
            )
        }.let {
            PlayerFrameworkImp(
                mediaControllerBuilder = it,
                _playerState = MutableStateFlow(PlayerState.Stopped),
            )
        }
    }

}