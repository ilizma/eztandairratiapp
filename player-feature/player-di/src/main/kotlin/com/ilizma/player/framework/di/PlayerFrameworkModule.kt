package com.ilizma.player.framework.di

import android.content.ComponentName
import android.content.Context
import com.ilizma.player.framework.PlayerFramework
import com.ilizma.player.framework.PlayerFrameworkImp
import com.ilizma.player.framework.model.PlayerState
import com.ilizma.player.framework.service.MusicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Singleton

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
    ).let { playerFrameworkImp(context, it) }

    private fun playerFrameworkImp(
        context: Context,
        componentName: ComponentName
    ): PlayerFrameworkImp = PlayerFrameworkImp(
        context = context,
        serviceComponent = componentName,
        playerState = BehaviorSubject.createDefault(PlayerState.Stopped),
        playerConnectionState = BehaviorSubject.create(),
    )

}