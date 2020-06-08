package com.ilizma.eztandairratiapp.di.module.presentation

import android.content.ComponentName
import android.content.Context
import com.ilizma.presentation.media.MusicService
import com.ilizma.presentation.media.MusicServiceConnection
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MediaModule {

    @Provides
    @Singleton
    fun provideMusicServiceConnection(context: Context): MusicServiceConnection =
        MusicServiceConnection(context, ComponentName(context, MusicService::class.java))

}
