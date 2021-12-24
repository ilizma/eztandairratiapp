package com.ilizma.player.framework.media.di

import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object AudioFocusRequestBuilderModule {

    @Provides
    @RequiresApi(Build.VERSION_CODES.O)
    fun provideAudioFocusRequestBuilder(
    ): AudioFocusRequest.Builder = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
        .let { audioFocusRequestBuilderParams(it) }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun audioFocusRequestBuilderParams(
        builder: AudioFocusRequest.Builder
    ): AudioFocusRequest.Builder = AudioAttributes.Builder()
        .let { buildAudioAttributes(it) }
        .let { builder.setAudioAttributes(it) }
        .let { it.setAcceptsDelayedFocusGain(true) }

    private fun buildAudioAttributes(
        builder: AudioAttributes.Builder
    ): AudioAttributes = builder.setUsage(AudioAttributes.USAGE_MEDIA)
        .let { it.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC) }
        .let { it.build() }

}