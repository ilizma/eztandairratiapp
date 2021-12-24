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
import io.reactivex.rxjava3.subjects.BehaviorSubject

@Module
@InstallIn(FragmentComponent::class)
object PlayerFrameworkModule {

    @Provides
    fun providePlayerFramework(
        context: Context,
    ): PlayerFramework = ComponentName(
        context,
        MusicService::class.java,
    ).let {
        PlayerFrameworkImp(
            context = context,
            serviceComponent = it,
            playerState = BehaviorSubject.create(),
        )
    }

}