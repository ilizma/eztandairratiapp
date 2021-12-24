package com.ilizma.player.framework.media.di

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.PowerManager
import com.ilizma.player.di.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object MediaPlayerModule {

    @Provides
    fun provideMediaPlayer(
        context: Context,
    ): MediaPlayer = MediaPlayer()
        .apply {
            setDataSource(BuildConfig.AUDIO_URL)
            setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
            setAudioAttributes(
                AudioAttributes.Builder().run {
                    setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    build()
                }
            )
            isLooping = false
        }

}