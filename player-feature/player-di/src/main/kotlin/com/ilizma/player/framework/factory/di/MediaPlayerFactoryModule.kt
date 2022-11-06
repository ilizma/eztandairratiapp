package com.ilizma.player.framework.factory.di

import android.media.MediaPlayer
import com.ilizma.player.framework.factory.MediaPlayerFactory
import com.ilizma.player.framework.factory.MediaPlayerFactoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent

@Module
@InstallIn(ServiceComponent::class)
object MediaPlayerFactoryModule {

    @Provides
    fun provideMediaPlayerFactory(
    ): MediaPlayerFactory<MediaPlayer> = MediaPlayerFactoryImp()

}