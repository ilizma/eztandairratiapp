package com.ilizma.player.framework.factory.di

import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import com.ilizma.player.framework.factory.MediaSessionBuilderFactory
import com.ilizma.player.framework.factory.MediaSessionBuilderFactoryImp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val mediaSessionBuilderFactoryModule: Module = module {

    factory<MediaSessionBuilderFactory<MediaSession.Builder, ExoPlayer>> {
        MediaSessionBuilderFactoryImp(context = androidContext())
    }

}