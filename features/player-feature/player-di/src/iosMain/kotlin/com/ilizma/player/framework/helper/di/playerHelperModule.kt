package com.ilizma.player.framework.helper.di

import com.ilizma.player.framework.helper.MusicHelper
import org.koin.core.module.Module
import org.koin.dsl.module

val playerHelperModule: Module = module {

    single<MusicHelper> {
        MusicHelper()
    }

}