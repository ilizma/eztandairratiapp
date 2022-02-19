package com.ilizma.player.framework.media.di

import android.support.v4.media.session.PlaybackStateCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent

@Module
@InstallIn(ServiceComponent::class)
object PlaybackStateCompatBuilderModule {

    @Provides
    fun providePlaybackStateCompatBuilder(
    ): PlaybackStateCompat.Builder = PlaybackStateCompat.Builder()

}