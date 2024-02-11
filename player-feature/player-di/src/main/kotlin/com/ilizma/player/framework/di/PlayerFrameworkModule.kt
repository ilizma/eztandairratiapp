package com.ilizma.player.framework.di

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.util.UnstableApi
import com.ilizma.player.framework.PlayerFramework
import com.ilizma.player.framework.PlayerFrameworkImp
import com.ilizma.player.framework.model.PlayerState
import com.ilizma.player.framework.service.MusicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Singleton

@UnstableApi
@Module
@InstallIn(SingletonComponent::class)
object PlayerFrameworkModule {

    @Provides
    @Singleton
    fun providePlayerFramework(
        @ApplicationContext context: Context,
    ): PlayerFramework = ComponentName(
        context,
        MusicService::class.java,
    ).let {
        PlayerFrameworkImp(
            context = context,
            serviceComponent = it,
            playerState = MutableStateFlow(PlayerState.Stopped),
        )
    }

}