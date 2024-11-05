package com.ilizma.player.framework.content.di

import android.content.IntentFilter
import android.media.AudioManager
import org.koin.core.module.Module
import org.koin.dsl.module

val noisyAudioIntentFilterModule: Module = module {

    factory<IntentFilter> { IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY) }

}