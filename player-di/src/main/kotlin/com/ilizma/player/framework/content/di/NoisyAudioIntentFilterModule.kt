package com.ilizma.player.framework.content.di

import android.content.IntentFilter
import android.media.AudioManager
import androidx.media.MediaBrowserServiceCompat.BrowserRoot
import com.ilizma.player.di.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object NoisyAudioIntentFilterModule {

    @Provides
    fun provideNoisyAudioIntentFilter(
    ): IntentFilter = IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY)
}