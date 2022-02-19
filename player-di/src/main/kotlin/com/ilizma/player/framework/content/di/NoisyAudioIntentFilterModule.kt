package com.ilizma.player.framework.content.di

import android.content.IntentFilter
import android.media.AudioManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent

@Module
@InstallIn(ServiceComponent::class)
object NoisyAudioIntentFilterModule {

    @Provides
    fun provideNoisyAudioIntentFilter(
    ): IntentFilter = IntentFilter(
        AudioManager.ACTION_AUDIO_BECOMING_NOISY
    )

}