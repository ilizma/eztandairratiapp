package com.ilizma.player.framework.factory.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import com.ilizma.player.framework.factory.MediaSessionBuilderFactory
import com.ilizma.player.framework.factory.MediaSessionBuilderFactoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ServiceComponent::class)
object MediaSessionBuilderFactoryModule {

    @Provides
    fun provideMediaSessionBuilderFactory(
        @ApplicationContext context: Context,
    ): MediaSessionBuilderFactory<MediaSession.Builder, ExoPlayer> = MediaSessionBuilderFactoryImp(
        context = context
    )

}