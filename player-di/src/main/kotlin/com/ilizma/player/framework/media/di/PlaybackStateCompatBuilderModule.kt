package com.ilizma.player.framework.media.di

import android.support.v4.media.session.PlaybackStateCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object PlaybackStateCompatBuilderModule {

    @Provides
    fun providePlaybackStateCompatBuilder(
    ): PlaybackStateCompat.Builder = PlaybackStateCompat.Builder()

}