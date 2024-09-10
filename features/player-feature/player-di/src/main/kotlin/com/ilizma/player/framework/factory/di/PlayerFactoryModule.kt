package com.ilizma.player.framework.factory.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.ilizma.player.framework.factory.PlayerFactory
import com.ilizma.player.framework.factory.PlayerFactoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ServiceComponent::class)
object PlayerFactoryModule {

    @Provides
    fun providePlayerFactory(
        @ApplicationContext context: Context,
    ): PlayerFactory<ExoPlayer> = PlayerFactoryImp(
        context = context
    )

}