package com.ilizma.player.framework.updater.di

import com.ilizma.main.view.widget.updater.PlayerWidgetUpdaterImp
import com.ilizma.player.framework.updater.PlayerWidgetUpdater
import org.koin.core.module.Module
import org.koin.dsl.module

val playerWidgetUpdaterModule: Module = module {

    factory<PlayerWidgetUpdater> { PlayerWidgetUpdaterImp() }

}