package com.ilizma.player.framework.di

import android.content.ComponentName
import android.content.Context
import com.ilizma.player.framework.PlayerFramework
import com.ilizma.player.framework.PlayerFrameworkImp
import com.ilizma.player.framework.service.MusicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import io.reactivex.rxjava3.subjects.BehaviorSubject

@Module
@InstallIn(FragmentComponent::class)
object PlayerFrameworkModule {

    @Provides
    fun providePlayerFramework(
        @ActivityContext context: Context,
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
        playerState = BehaviorSubject.create(),
        playerConnectionState = BehaviorSubject.create(),
    )

}