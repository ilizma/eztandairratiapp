package com.ilizma.app.di

import com.ilizma.api.di.apiModules
import com.ilizma.menu.di.menuModules
import com.ilizma.net.di.netModules
import com.ilizma.player.di.playerModules
import com.ilizma.schedule.di.scheduleModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    config: KoinAppDeclaration? = null,
    platformModules: List<Module>? = null,
) {
    startKoin {
        config?.invoke(this)
        mutableListOf<Module>()
            .apply {
                addAll(apiModules)
                addAll(menuModules)
                addAll(netModules)
                addAll(playerModules)
                addAll(scheduleModules)
                platformModules?.let { addAll(it) }
            }.let { modules(it) }
    }
}