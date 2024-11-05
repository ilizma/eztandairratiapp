package com.ilizma.player.framework.factory.di

import androidx.media3.exoplayer.ExoPlayer
import com.ilizma.player.framework.factory.PlayerFactory
import com.ilizma.player.framework.factory.PlayerFactoryImp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val playerFactoryModule: Module = module {

    factory<PlayerFactory<ExoPlayer>> { PlayerFactoryImp(context = androidContext()) }

}